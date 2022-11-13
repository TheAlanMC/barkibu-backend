package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.RecoveryAccountBl;
import bo.edu.ucb.barkibu.dto.RecoveryAccountReqDto;
import bo.edu.ucb.barkibu.dto.RecoveryPasswordDto;
import bo.edu.ucb.barkibu.dto.RecoveryPasswordReqDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/recovery-account")
public class RecoveryAccountApi {
    RecoveryAccountBl recoveryAccountBl;

    public RecoveryAccountApi(RecoveryAccountBl recoveryAccountBl) {
        this.recoveryAccountBl = recoveryAccountBl;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createRecoveryAccount(@RequestBody RecoveryAccountReqDto recoveryAccountReqDto) {
        if (recoveryAccountReqDto.validate()) {
            try {
                recoveryAccountBl.createRecoveryAccount(recoveryAccountReqDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Security code sent to your email", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    @PostMapping("/hash-code")
    public ResponseEntity<ResponseDto<String>> validateCode(@RequestBody RecoveryPasswordReqDto recoveryPasswordReqDto) {
        if (recoveryPasswordReqDto.validate()) {
            try {
                recoveryAccountBl.validateCode(recoveryPasswordReqDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Code is valid", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    @PostMapping("/password")
    public ResponseEntity<ResponseDto<String>> updatePassword(@RequestBody RecoveryPasswordDto recoveryPasswordDto) {
        if (recoveryPasswordDto.validate()) {
            try {
                recoveryAccountBl.updatePassword(recoveryPasswordDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Password updated successfully", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }
}
