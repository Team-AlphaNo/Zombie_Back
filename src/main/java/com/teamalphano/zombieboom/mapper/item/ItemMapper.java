package com.teamalphano.zombieboom.mapper.item;

import com.teamalphano.zombieboom.dto.item.ItemListDto;
import com.teamalphano.zombieboom.model.item.ItemData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemData getCharData(Integer itemNo);

    //관리자 - 아이템 목록
    List<ItemData> getItemListAdmin(ItemListDto itemListDto);

    //상품 상세 - 아이템 목록
    List<ItemData> getProductItemListAdmin(Integer prodNo);

    int insertItemAdmin(ItemData itemData);

    int updateItemAdmin(ItemData itemData);
}
