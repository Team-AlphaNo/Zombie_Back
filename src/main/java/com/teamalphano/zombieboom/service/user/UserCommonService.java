package com.teamalphano.zombieboom.service.user;

import com.teamalphano.zombieboom.common.CharStringEdit;
import com.teamalphano.zombieboom.dto.user.UserDataDto;
import com.teamalphano.zombieboom.dto.user.UserFullDataDto;
import com.teamalphano.zombieboom.dto.user.UserInfoDto;
import com.teamalphano.zombieboom.mapper.item.ItemMapper;
import com.teamalphano.zombieboom.mapper.user.UserInfoMapper;
import com.teamalphano.zombieboom.model.item.ItemData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCommonService {
    private final UserInfoMapper userInfoMapper;
    private final ItemMapper itemMapper;

    public UserCommonService(UserInfoMapper userInfoMapper, ItemMapper itemMapper) {
        this.userInfoMapper = userInfoMapper;
        this.itemMapper = itemMapper;
    }

    //전체 유저 데이터 조회
    public UserFullDataDto getUserFullData(Integer userNo) {
        // UserFullData 초기화
        UserFullDataDto userFullDataDto = new UserFullDataDto();
        UserInfoDto userInfoDto = userInfoMapper.getUserInfoByUserNo(userNo);
        UserDataDto userDataDto = userInfoMapper.getUserDataByUserNo(userNo);

        userFullDataDto.setUserInfo(userInfoDto);
        userFullDataDto.setUserData(userDataDto);

        // UserData에서 charList 가져오기
        String charList = userDataDto.getUserCharList();
        if (charList == null || charList.isEmpty()) {
            return userFullDataDto;
        }

        try {
            // 문자열에서 대괄호 제거 및 파싱
            CharStringEdit charStringEdit = new CharStringEdit();
            List<Integer> charNoList = charStringEdit.getIntList(charList);

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

            userDataDto.setUserCharDataList(charDataList);

        } catch (NumberFormatException e) {
            System.err.println("Failed to parse UserCharList: " + charList);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred while processing UserCharList for userNo: " + userNo);
            e.printStackTrace();
        }
        return userFullDataDto;
    }

}
