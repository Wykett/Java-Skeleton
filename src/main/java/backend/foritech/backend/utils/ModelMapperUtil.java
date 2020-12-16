package backend.foritech.backend.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelMapperUtil {

    @Autowired
    private static ModelMapper modelMapper;

    public static Object convertToBean(Object entity) {
        return modelMapper.map(entity, Object.class);
    }
}
