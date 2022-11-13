package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Veterinary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface VeterinaryDao {
    // Encuentra el id de un veterinario por su userName
    @Select("""
            SELECT veterinary.name, address, latitude, longitude, veterinary.description from veterinary
            JOIN "user" ON veterinary.user_id = "user".user_id
            WHERE user_name = #{userName}
            AND veterinary.status = 'activo'
            AND "user".status = 'activo';
            """)
    Veterinary findVeterinaryByUserName(String userName);

    @Insert("""
            INSERT INTO veterinary (user_id, name, address, latitude, longitude, description, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{name}, #{address}, #{latitude}, #{longitude}, #{description}, 'activo', now(), 'anonymous', 'localhost');
            """)
    void createVeterinary(Veterinary veterinary);



}
