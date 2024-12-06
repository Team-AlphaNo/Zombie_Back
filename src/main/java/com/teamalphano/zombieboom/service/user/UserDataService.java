package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.user.UserBuyDto;
import com.teamalphano.zombieboom.mapper.character.CharacterMapper;
import com.teamalphano.zombieboom.mapper.shop.ShopMapper;
import com.teamalphano.zombieboom.mapper.user.UserDataMapper;
import com.teamalphano.zombieboom.model.character.CharacterData;
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
    private final CharacterMapper characterMapper;

    public UserDataService(UserDataMapper userDataMapper, ShopMapper shopMapper, CharacterMapper characterMapper) {
        this.userDataMapper = userDataMapper;
        this.shopMapper = shopMapper;
        this.characterMapper = characterMapper;
    }

    //상품 구입
    public UserData buyProduct(UserBuyDto userBuyDto) {
        Product productParam = new Product();
        productParam.setProdNo(userBuyDto.getProdNo());

        //상품 상세 데이터
        Product product = shopMapper.getProductDetail(productParam);
        if (product != null) {
            if(product.getProdLimit() && product.getProdPurchaseCount()<=0){
                System.out.println("상품 다 팔림");
                return null;
            }
            List<ProductItem> items = shopMapper.getProductItem(userBuyDto.getProdNo());
            product.setItems(items);
        }else{
            System.out.println("상품 없음");
            return null;
        }

        List<Integer> charList = new ArrayList<>();
        int coin = 0;
        int ticket = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime totalTime = LocalTime.of(0, 0, 0); // 초기 시간값 (00:00:00)

        if(product.getItems() != null){
            for(ProductItem items : product.getItems()){
                if(items.getItemType() ==1){
                    //캐릭터
                    //todo: test
                    //charList.add(items.getCharNo());
                }else if(items.getItemType() ==2){
                    // 냥코인
                    coin++;
                }else{
                    //츄르
                    if(items.getItemTime().isEmpty()) {
                        ticket++;
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

        if(userBuyDto.getIsCoin()){
            coin -= product.getProdPrice();
        }

        // DTO에 데이터 설정
        userBuyDto.setCharList(jsonString);
        userBuyDto.setCoin(coin);
        userBuyDto.setTicket(ticket);

        //유저 데이터 갱신
        int updateUserData = userDataMapper.updateUserDataAfterPurchase(userBuyDto);
        if(updateUserData > 0){
            //상점 데이터 갱신
            int updateProdData = shopMapper.updateProductAfterPurchase(userBuyDto);
            if(updateProdData > 0){
                //갱신한 유저 데이터 조회
                UserData userData = userDataMapper.getUserData(userBuyDto.getUserNo());
                return addCharDataToUserData(userData);
            }else{
                return null;
            }
        }else {
            return null;
        }
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
            List<CharacterData> charDataList = new ArrayList<>();
            for(Integer charNo : charNoList){
                //유저 상세 캐릭터 데이터 조회
                charDataList.add(characterMapper.getCharDataList(charNo));
            }
            userData.setUserCharDataList(charDataList);
        }
        return userData;
    }
}
