package mybatis.mapper;

import VO.MVCBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MVCBoardMapper {
    int selectCount(Map<String, Object> map);
    List<MVCBoardVO> selectListPage(Map<String, Object> map);
    int updateVisitCount(String idx);
    MVCBoardVO selectView(String idx);
    int insertWrite(MVCBoardVO vo);
    int downCountPlus(String idx);
    boolean confirmPassword(String pass, String idx);

    int confirmPassword(Map<String, Object> map);

    int deletePost(String idx);

    int updatePost(MVCBoardVO vo);
}
