package com.teamalphano.zombieboom.service.item;

import com.teamalphano.zombieboom.dto.item.ReqItemRelationDto;
import com.teamalphano.zombieboom.dto.item.RelationDto;
import com.teamalphano.zombieboom.mapper.item.ItemRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemRelationService {
    private final ItemRelationMapper itemRelationMapper;

    public ItemRelationService(ItemRelationMapper itemRelationMapper) {this.itemRelationMapper = itemRelationMapper;}

    //연관정보 추가
    public String addItemRelationsToProd(ReqItemRelationDto reqItemRelationDto){
        if(reqItemRelationDto.getItemNoList().isEmpty())
            return "ItemNo is Empty";
        List<Integer> itemNos = reqItemRelationDto.getItemNoList();
        for ( int itemNo : itemNos){
            RelationDto relationDto = new RelationDto();
            relationDto.setItemNo(itemNo);
            relationDto.setProdNo(reqItemRelationDto.getProdNo());
            itemRelationMapper.addItemRelationsToProd(relationDto);
        }
        return "Success";
    }

    //연관정보 삭제
    public String removeItemRelationsToProd(ReqItemRelationDto reqItemRelationDto){
        if(reqItemRelationDto.getItemNoList().isEmpty())
            return "ItemNo is Empty";
        List<Integer> itemNos = reqItemRelationDto.getItemNoList();
        for ( int itemNo : itemNos){
            RelationDto relationDto = new RelationDto();
            relationDto.setItemNo(itemNo);
            relationDto.setProdNo(reqItemRelationDto.getProdNo());
            itemRelationMapper.removeItemRelationsToProd(relationDto);
        }
        return "Success";
    }
}
