package com.teamalphano.zombieboom.mapper.item;

import com.teamalphano.zombieboom.model.item.ItemData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {

    ItemData getCharData(Integer itemNo);
}
