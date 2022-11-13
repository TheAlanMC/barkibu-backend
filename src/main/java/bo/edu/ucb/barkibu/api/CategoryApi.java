package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.CategoryBl;
import bo.edu.ucb.barkibu.dto.CategoryDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.entity.Category;
import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/category")
public class CategoryApi {
    private final CategoryBl categoryBl;

    public CategoryApi(CategoryBl categoryBl) {
        this.categoryBl = categoryBl;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getAnswers(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            List<CategoryDto> categoryDtos = categoryBl.findAllCategories();
            ResponseDto<List<CategoryDto>> responseDto = new ResponseDto<>(categoryDtos, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }
}
