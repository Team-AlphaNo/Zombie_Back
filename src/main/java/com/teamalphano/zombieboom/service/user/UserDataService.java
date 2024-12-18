package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.logs.PaymentCreateDto;
import com.teamalphano.zombieboom.dto.user.UserBuyDto;
import com.teamalphano.zombieboom.dto.user.UserBuyParamsDto;
import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import com.teamalphano.zombieboom.mapper.logs.PaymentLogsMapper;
import com.teamalphano.zombieboom.mapper.shop.ShopMapper;
import com.teamalphano.zombieboom.mapper.user.UserDataMapper;
import com.teamalphano.zombieboom.model.item.ItemData;
import com.teamalphano.zombieboom.model.shop.Product;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import com.teamalphano.zombieboom.model.user.UserData;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService {
    private final UserDataMapper userDataMapper;
    private final ShopMapper shopMapper;
    private final ItemMapper itemMapper;
    private final PaymentLogsMapper paymentLogsMapper;

    public UserDataService(
            UserDataMapper userDataMapper,
            ShopMapper shopMapper,
            ItemMapper itemMapper,
            PaymentLogsMapper paymentLogdMapper
    ) {
        this.userDataMapper = userDataMapper;
        this.shopMapper = shopMapper;
        this.itemMapper = itemMapper;
        this.paymentLogsMapper = paymentLogdMapper;
    }

    //상품 구입 - 코인
    public UserData buyProduct(UserBuyParamsDto userBuyParamsDto) {
        Product product = checkProduct(userBuyParamsDto.getProdId());
        if(product != null) {
            UserBuyDto userBuyDto = buyProdCheck(product);
            userBuyDto.setProdNo(product.getProdNo());
            userBuyDto.setUserNo(userBuyParamsDto.getUserNo());

            //유저 데이터 갱신
            int updateUserData = userDataMapper.updateUserDataAfterPurchase(userBuyDto);
            if(updateUserData > 0){
                //상점 데이터 갱신
                int updateProdData = shopMapper.updateProductAfterPurchase(userBuyDto);
                if(updateProdData > 0){
                    //갱신한 유저 데이터 조회
                    PaymentCreateDto paymentCreateDto = new PaymentCreateDto();
                    paymentCreateDto.setUserNo(userBuyDto.getUserNo());
                    paymentCreateDto.setPaymentStatus(true);
                    paymentCreateDto.setProdNo(product.getProdNo());

                    // 구입 로그 추가
                    paymentLogsMapper.insertPaymentLog(paymentCreateDto);
                    UserData userData = userDataMapper.getUserData(userBuyDto.getUserNo());
                    return addCharDataToUserData(userData);
                }else{
                    return null;
                }
            }else {
                return null;
            }
        }else{
            return null;
        }
    }

    private Product checkProduct(String prodId){
        Product productParam = new Product();
        productParam.setProdId(prodId);

        //상품 상세 데이터
        Product product = shopMapper.getProductDetail(productParam);
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

    private UserBuyDto buyProdCheck(Product product) {
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
            userBuyDto.setUserTicketTimer(totalTime.format(formatter));
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
    public UserData getUserData(Integer userNo) {
        UserData userData = userDataMapper.getUserData(userNo);
        return addCharDataToUserData(userData);
    }

    //공통 유저 캐릭터 데이터 list
    private UserData addCharDataToUserData(UserData userData) {
        if(!userData.getUserCharList().equals("[]")){
            CharStringEdit charStringEdit = new CharStringEdit();
            List<Integer> charNoList = charStringEdit.getIntList(userData.getUserCharList());

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
            userData.setUserCharDataList(charDataList);
        }
        return userData;
    }
}
