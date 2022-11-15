package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface AnswerDao {

    @Insert("""
            INSERT INTO answer
            (question_id, user_id, answer, time_stamp, status, tx_date, tx_user, tx_host)
            VALUES
            (#{questionId}, #{userId}, #{answer}, now(), 'activo', now(), 'anonymus', 'localhost')
            """)
    void addAnswer(Answer answer);

    @Update("""
            UPDATE answer
            SET answer = #{answer}
            WHERE question_id = #{questionId}
            AND user_id = #{userId}
            AND status='activo'
            """)
    void updateAnswer(Answer answer);

    @Select("""
            SELECT answer_id
            FROM answer
            WHERE question_id = #{questionId}
            AND user_id = #{userId}
            AND status='activo';
            """)
    Integer findAnswerIdByQuestionIdAndUserId(Integer questionId, Integer userId);

    @Select("""
            SELECT answer_id
            FROM answer
            WHERE answer_id = #{answerId}
            AND status='activo';
            """)
    Integer findAnswerIdByAnswerId(Integer answerId);

    @Select("""
            SELECT user_answer_like_id
            FROM user_answer_like
            WHERE answer_id = #{answerId}
            AND user_id = #{userId}
            AND liked = true
            AND status='activo';
            """)
    Integer findUserAnswerLikeIdByAnswerIdAndUserId(Integer answerId, Integer userId);

    @Insert("""
            INSERT INTO user_answer_like
            (user_id, answer_id, liked, status, tx_date, tx_user, tx_host)
            VALUES
            (#{userId}, #{answerId}, true, 'activo', now(), 'anonymus', 'localhost')
            """)
    void likeAnswer(Integer answerId, Integer userId);
}
