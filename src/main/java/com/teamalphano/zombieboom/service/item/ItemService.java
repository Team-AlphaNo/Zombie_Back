package com.teamalphano.zombieboom.service.item;

import com.teamalphano.zombieboom.dto.item.ItemListDto;
import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import com.teamalphano.zombieboom.model.item.ItemData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemMapper itemMapper;

    public ItemService(ItemMapper itemMapper) {this.itemMapper = itemMapper;}


    public List<ItemData> getItemListAdmin (ItemListDto itemListDto){
        //TODO: 검색조건 수정하기
        return itemMapper.getItemListAdmin(itemListDto);
    }

    //상품 상세 아이템 목록
    public List<ItemData> getProductItemListAdmin(Integer prodNo){
        return itemMapper.getProductItemListAdmin(prodNo);
    }

    //상품 등록
    public String insertItemAdmin(ItemData itemData){
        int insert = itemMapper.insertItemAdmin(itemData);
        if(insert > 0){
            return "Success";
        }else{
            return "Fail";
        }
    }

    public String updateItemAdmin(ItemData itemData){
        int update = itemMapper.updateItemAdmin(itemData);
        if(update > 0){
            return "Success";
        }else{
            return "Fail";
        }
    }
}
