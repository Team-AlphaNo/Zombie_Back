package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.purchase.DeductAmountDto;
import com.teamalphano.zombieboom.dto.purchase.PurchaseGrantDto;
import com.teamalphano.zombieboom.dto.shop.ProductDto;
import com.teamalphano.zombieboom.dto.user.*;
import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import com.teamalphano.zombieboom.mapper.shop.ShopMapper;
import com.teamalphano.zombieboom.mapper.user.UserDataMapper;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.model.item.ItemData;
import com.teamalphano.zombieboom.model.shop.ProductItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService {
    private final UserDataMapper userDataMapper;
    private final ShopMapper shopMapper;
    private final ItemMapper itemMapper;
    private final UserCommonService userCommonService;
    private final UserInfoMapper userInfoMapper;

    public UserDataService(
            UserDataMapper userDataMapper,
            ShopMapper shopMapper,
            ItemMapper itemMapper,
            UserCommonService userCommonService,
            UserInfoMapper userInfoMapper) {
        this.userDataMapper = userDataMapper;
        this.shopMapper = shopMapper;
        this.itemMapper = itemMapper;
        this.userCommonService = userCommonService;
        this.userInfoMapper = userInfoMapper;
    }

    //상품 구입시 - 금액 차감
    @Transactional
    public boolean deductAmount(DeductAmountDto deductAmountDto){
        int deduct = userDataMapper.deductUserCoin(deductAmountDto);
        return deduct > 0;
    }


    //상품 구입시 - 상품지급
    @Transactional
    public boolean userGrantProduct(PurchaseGrantDto purchaseGrantDto) {
        ProductDto product = checkProduct(purchaseGrantDto.getProdId());
        if(product != null) {
            UserBuyDto userBuyDto = buyProdCheck(product);
            if(userBuyDto == null) {
                throw new RuntimeException("No Product Items");
            }
            userBuyDto.setProdNo(product.getProdNo());
            userBuyDto.setUserNo(purchaseGrantDto.getUserNo());

            CharStringEdit charStringEdit = new CharStringEdit();

            UserDataDto _userDataDto = userDataMapper.getUserData(userBuyDto.getUserNo());

            System.out.println("update---------"+userBuyDto.toString());

            if(product.isUnique()){
                System.out.println("unique");
                if(_userDataDto.getUniqProdList() !=null) {
                    String uniqList = _userDataDto.getUniqProdList();

                    List<Integer> _uniqProdList = charStringEdit.getIntList(uniqList);
                    _uniqProdList.add(product.getProdNo());
                    userBuyDto.setUniqProdList(charStringEdit.getListToString(_uniqProdList));
                }else{
                    List<Integer> _uniqProdList = new ArrayList<>();
                    _uniqProdList.add(product.getProdNo());
                    userBuyDto.setUniqProdList(charStringEdit.getListToString(_uniqProdList));
                }
            }

            if(userBuyDto.getCharList() !=null && !userBuyDto.getCharList().isEmpty()){
                System.out.println("charList");
                String charList = _userDataDto.getUserCharList();

                List<Integer> _charList = charStringEdit.getIntList(charList);
                List<Integer> newCharList = charStringEdit.getIntList(userBuyDto.getCharList());
                _charList.addAll(newCharList);
                userBuyDto.setCharList(charStringEdit.getListToString(_charList));
            }

            //유저 데이터 갱신
            int updateUserData = userDataMapper.updateAfterPurchase(userBuyDto);
            if(updateUserData > 0) {
                if(userBuyDto.getTimeTicketRange() != null && userBuyDto.getTimeTicketRange()!=0) {
                    userInfoMapper.updateChargeTimeNow(purchaseGrantDto.getUserNo());
                }
                return true;
            }else {
                return false;
            }
        }else{
            System.out.println("no product");
            return false;
        }
    }

    @Transactional
    public UserFullDataDto useUserTicket(Integer userNo){
        int update = userDataMapper.useUserTicket(userNo);
        if(update > 0) {
            return userCommonService.getUserFullData(userNo);
        }else{
            throw new RuntimeException("Failed to use user ticket");
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
            List<ProductItem> items = shopMapper.getProductItemByNo(product.getProdNo());
            product.setItems(items);

            return product;
        }else{
            System.out.println("상품 없음");
            return null;
        }
    }

    private UserBuyDto buyProdCheck(ProductDto product) {
        UserBuyDto userBuyDto = new UserBuyDto();

        System.out.println("--------------------" + product.toString());
        System.out.println("++++++++++++" + product.getItems().toString());

        List<Integer> charList = new ArrayList<>();
        int coin = 0;
        int ticket = 0;
        int totalSeconds = 0; // 시간 누적을 위한 변수 (초 단위)

        if (product.getItems() != null) {
            for (ProductItem items : product.getItems()) {
                System.out.println("---------check+++++++++++++" + items);

                if (items.getItemType() == 1) {
                    // 캐릭터
                    charList.add(items.getItemNo());
                } else if (items.getItemType() == 2) {
                    // 코인
                    System.out.println("coin ========" + coin);
                    coin += items.getItemCount();
                } else if (items.getItemType() == 3) {
                    // 열쇠
                    System.out.println("key time : "+items.getItemTime());
                    String itemTime = items.getItemTime();
                    if (itemTime != null && !itemTime.isEmpty()) {
                        totalSeconds += parseTimeToSeconds(itemTime);
                        System.out.println("totalSeconds ========" + totalSeconds);
                    } else {
                        ticket += items.getItemCount();
                        System.out.println("total ticket ========" + ticket);
                    }
                }
            }
        }

        System.out.println("--------------------전체 넣을 데이터 설정 완료");

        // 시간 범위 설정
        if (totalSeconds > 0) {
            userBuyDto.setTimeTicketRange(totalSeconds);
        } else {
            userBuyDto.setTimeTicketRange(null);
        }

        // 캐릭터 리스트 설정
        if (!charList.isEmpty()) {
            CharStringEdit charStringEdit = new CharStringEdit();
            String charListString = charStringEdit.getListToString(charList);
            userBuyDto.setCharList(charListString);
        } else {
            userBuyDto.setCharList("");
        }

        // DTO에 데이터 설정
        userBuyDto.setProdId(product.getProdId());
        userBuyDto.setCoin(coin);
        userBuyDto.setTicket(ticket);

        System.out.println("--------------------userBuyDto+++++" + userBuyDto.toString());
        return userBuyDto;
    }

    //유저 데이터 조회
    public UserDataDto getUserData(Integer userNo) {
        UserDataDto userDataDto = userDataMapper.getUserData(userNo);
        return addCharDataToUserData(userDataDto);
    }

    //공통 유저 캐릭터 데이터 list
    private UserDataDto addCharDataToUserData(UserDataDto userDataDto) {
        if (userDataDto.getUserCharList() == null || userDataDto.getUserCharList().isEmpty() ) {
            return userDataDto;
        }

        CharStringEdit charStringEdit = new CharStringEdit();
        List<Integer> charNoList = charStringEdit.getIntList(userDataDto.getUserCharList());

        System.out.println("Parsed Character IDs: " + charNoList);

        // 캐릭터 데이터 리스트 생성
        if (charNoList.isEmpty()) {
            System.out.println("Character list is empty.");
            return userDataDto;
        }

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
        return userDataDto;
    }

    //전체 데이터로 유저 데이터 업데이트
    public void userDataUpdateByFullData(UserFullDataDto userFullDataDto) {
        Integer userNo = userFullDataDto.getUserInfo().getUserNo();
        UpdateUserDataDto updateUserDataDto = new UpdateUserDataDto();
        BeanUtils.copyProperties(userFullDataDto.getUserData(), updateUserDataDto);
        updateUserDataDto.setUserNo(userNo);
        userDataMapper.updateUserData(updateUserDataDto);
    }

    @Transactional
    public int updateUserTicket(UpdateTicketDto updateTicketDto){
        return userDataMapper.updateUserTicket(updateTicketDto);
    }

    /**
     * HH:mm:ss 형식의 시간을 초 단위로 변환.
     */
    private int parseTimeToSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    /**
     * 초 단위를 HH:mm:ss 형식으로 변환.
     */
    private String formatSecondsToHHMMSS(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
