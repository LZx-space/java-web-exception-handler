package org.lzx.java.response.status.server.service;

import org.lzx.java.response.status.server.model.ExceptionCodeVo;
import org.lzx.java.response.status.server.repository.ExceptionCodePoRepository;
import org.lzx.java.response.status.server.support.ExceptionCodeSpecification;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author LZx
 * @since 2023/1/9
 */
@Service
public class DefaultExceptionCodeService implements ExceptionCodeService {

    public final ExceptionCodePoRepository exceptionCodePoRepository;

    public DefaultExceptionCodeService(ExceptionCodePoRepository exceptionCodePoRepository) {
        this.exceptionCodePoRepository = exceptionCodePoRepository;
    }

    @Override
    public Optional<ExceptionCodeVo> findByCode(String exceptionCode) {
        if (!ExceptionCodeSpecification.isConform(exceptionCode)) {
            return Optional.empty();
        }
        return exceptionCodePoRepository.findById(exceptionCode)
                .map(po -> {
                    ExceptionCodeVo dto = new ExceptionCodeVo();
                    dto.setCode(exceptionCode);
                    dto.setSourceType(po.getSourceType().getSource());
                    dto.setBusinessCode(po.getBusinessCode());
                    dto.setBusiness(po.getBusiness());
                    dto.setReason(po.getReason());
                    return dto;
                });
    }

}
