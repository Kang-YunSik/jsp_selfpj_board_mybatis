package DAO;

import VO.MVCBoardVO;
import mybatis.factory.MyBatisSessionFactory;
import mybatis.mapper.MVCBoardMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MVCBoardDAO {
    public int selectCount(Map<String, Object> map){
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.selectCount(map);
        sqlSession.close();
        return result;
    }

    public List<MVCBoardVO> selectListPage(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        List<MVCBoardVO> result = mapper.selectListPage(map);
        sqlSession.close();
        return result;
    }

    public void updateVisitCount(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.updateVisitCount(idx);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("조회수 증가 중 오류 발생");
        }
        sqlSession.close();
    }

    public MVCBoardVO selectView(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        MVCBoardVO vo = mapper.selectView(idx);
        sqlSession.close();
        return vo;
    }

    public int insertWrite(MVCBoardVO vo) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.insertWrite(vo);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    public void downCountPlus(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.downCountPlus(idx);
        if (result == 1) {
            sqlSession.commit();
        } else {
            System.out.println("다운로드 수 증가 중 오류 발생");
        }
        sqlSession.close();
    }

    public boolean confirmPassword(String pass, String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("pass", pass);
        map.put("idx", idx);

        int count = mapper.confirmPassword(map);
        sqlSession.close();

        return count > 0;
    }

    public int deletePost(String idx) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.deletePost(idx);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }


    public int updatePost(MVCBoardVO vo) {
        SqlSession sqlSession = MyBatisSessionFactory.getSqlSession();
        MVCBoardMapper mapper = sqlSession.getMapper(MVCBoardMapper.class);
        int result = mapper.updatePost(vo);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }
}
