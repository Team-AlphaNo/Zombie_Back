package com.teamalphano.zombieboom.mapper.character;

import com.teamalphano.zombieboom.model.character.CharacterData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CharacterMapper {

    CharacterData getCharDataList(int charNo);
}
