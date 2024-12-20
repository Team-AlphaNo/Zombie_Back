package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.purchase.DeductAmountDto;
import com.teamalphano.zombieboom.dto.purchase.PurchaseGrantDto;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
import com.teamalphano.zombieboom.dto.user.UpdateUserDataDto;
import com.teamalphano.zombieboom.dto.user.UserBuyDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import com.teamalphano.zombieboom.mapper.shop.ShopMapper;
import com.teamalphano.zombieboom.mapper.user.UserDataMapper;
import com.teamalphano.zombieboom.model.item.ItemData;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService {
    private final UserDataMapper userDataMapper;
    private final ShopMapper shopMapper;
    private final ItemMapper itemMapper;

    public UserDataService(
            UserDataMapper userDataMapper,
            ShopMapper shopMapper,
            ItemMapper itemMapper
    ) {
        this.userDataMapper = userDataMapper;
        this.shopMapper = shopMapper;
        this.itemMapper = itemMapper;
    }

    //상품 구입시 - 금액 차감
    @Transactional
    public boolean deductAmount(DeductAmountDto deductAmountDto){
        int deduct = userDataMapper.deductUserCoin(deductAmountDto);
        return deduct > 0;
    }


    //상품 구입시 - 상품지급
    @Transactional
    public UserDataDto userGrantProduct(PurchaseGrantDto purchaseGrantDto) {
        ProductDto product = checkProduct(purchaseGrantDto.getProdId());
        if(product != null) {
            UserBuyDto userBuyDto = buyProdCheck(product);
            userBuyDto.setProdNo(product.getProdNo());
            userBuyDto.setUserNo(purchaseGrantDto.getUserNo());

            //유저 데이터 갱신
            int updateUserData = userDataMapper.updateUserDataAfterPurchase(userBuyDto);
            if(updateUserData > 0) {
                return getUserData(userBuyDto.getUserNo());
            }else {
                return null;
            }
        }else{
            return null;
        }
    }

    private ProductDto checkProduct(String prodId){
        ProductDto productParam = new ProductDto();
        productParam.setProdId(prodId);

        //상품 상세 데이터
        ProductDto product = shopMapper.getProductDetailById(productParam);
        if (product != null) {
            if(product.isProdLimit() && product.getProdPurchaseCount()<=0){
                System.out.println("상품 다 팔림");
                return null;
            }
            List<ProductItem> items = shopMapper.getProductItemById(prodId);
            product.setItems(items);

            return product;
        }else{
            System.out.println("상품 없음");
            return null;
        }
    }

    private UserBuyDto buyProdCheck(ProductDto product) {
        UserBuyDto userBuyDto = new UserBuyDto();

        List<Integer> charList = new ArrayList<>();
        int coin = 0;
        int ticket = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime totalTime = LocalTime.of(0, 0, 0); // 초기 시간값 (00:00:00)

        if(product.getItems() != null){
            for(ProductItem items : product.getItems()){
                if(items.getItemType() ==1){
                    charList.add(items.getItemNo());
                }else if(items.getItemType() == 2){
                    // 코인
                    coin += items.getItemCount();
                }else{
                    //츄르
                    if(items.getItemTime().isEmpty()) {
                        ticket += items.getItemCount();
                    }else{
                        LocalTime t = LocalTime.parse(items.getItemTime(), formatter);
                        totalTime = totalTime.plusHours(t.getHour())
                                .plusMinutes(t.getMinute())
                                .plusSeconds(t.getSecond());
                    }
                }
            }
        }

        if(!totalTime.equals(LocalTime.of(0, 0, 0))){
            userBuyDto.setTimeTicketRange(totalTime.format(formatter));
        }

        String jsonString = "";
        if(!charList.isEmpty()){
            CharStringEdit charStringEdit = new CharStringEdit();
            userBuyDto.setCharList(charStringEdit.getListToString(charList));
        }

        coin -= product.getProdPrice();

        // DTO에 데이터 설정
        userBuyDto.setProdId(product.getProdId());
        userBuyDto.setCharList(jsonString);
        userBuyDto.setCoin(coin);
        userBuyDto.setTicket(ticket);

        return userBuyDto;
    }

    //유저 데이터 조회
    public UserDataDto getUserData(Integer userNo) {
        UserDataDto userDataDto = userDataMapper.getUserData(userNo);
        return addCharDataToUserData(userDataDto);
    }

    //공통 유저 캐릭터 데이터 list
    private UserDataDto addCharDataToUserData(UserDataDto userDataDto) {
        if(!userDataDto.getUserCharList().equals("[]")){
            CharStringEdit charStringEdit = new CharStringEdit();
            List<Integer> charNoList = charStringEdit.getIntList(userDataDto.getUserCharList());

            // CharacterData 리스트 생성
            List<ItemData> charDataList = new ArrayList<>();
            for (int charId : charNoList) {
                System.out.println("Processing charId: " + charId);
                ItemData charData = itemMapper.getCharData(charId);
                if (charData != null) {
                    charDataList.add(charData);
                } else {
                    System.err.println("No CharacterData found for charId: " + charId);
                }
            }
            userDataDto.setUserCharDataList(charDataList);
        }
        return userDataDto;
    }

    //전체 데이터로 유저 데이터 업데이트
    public void userDataUpdateByFullData(UserFullDataDto userFullDataDto) {
        Integer userNo = userFullDataDto.getUserInfo().getUserNo();
        UpdateUserDataDto updateUserDataDto = new UpdateUserDataDto();
        BeanUtils.copyProperties(userFullDataDto.getUserData(), updateUserDataDto);
        updateUserDataDto.setUserMoney(userNo);
        userDataMapper.updateUserData(updateUserDataDto);
    }
}
