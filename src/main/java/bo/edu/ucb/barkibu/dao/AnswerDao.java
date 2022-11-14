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
            (question_id, user_id, answer, time_stamp, pet_owner_like, veterinarian_like,status, tx_date, tx_user, tx_host)
            VALUES
            (#{questionId}, #{userId}, #{answer}, now(), 0, 0, 'activo', now(), 'anonymus', 'localhost')
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

    @Update("""
            UPDATE answer
            SET pet_owner_like = pet_owner_like + 1
            WHERE answer_id = #{answerId}
            AND status='activo';
            """)
    void increasePetOwnerLike(Integer answerId);

    @Update("""
            UPDATE answer
            SET veterinarian_like = veterinarian_like + 1
            WHERE answer_id = #{answerId}
            AND status='activo';
            """)
    void increaseVeterinarianLike(Integer answerId);
}
