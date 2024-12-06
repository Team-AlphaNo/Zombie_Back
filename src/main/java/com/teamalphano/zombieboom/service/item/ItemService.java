package com.teamalphano.zombieboom.service.item;

import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemMapper itemMapper;

    public ItemService(ItemMapper itemMapper) {this.itemMapper = itemMapper;}


}
