package com.teamalphano.zombieboom.mapper.item;

import com.teamalphano.zombieboom.dto.item.RelationDto;
import com.teamalphano.zombieboom.dto.purchase.UpdateProdPurchaseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  ItemRelationMapper {

    //prodNo로 아이템 연관 삭제
    int deleteItemRelationByProdNo(Integer prodNo);

    void addItemRelationsToProd(RelationDto relationDto);

    void removeItemRelationsToProd(RelationDto relationDto);
}
