package br.com.kme.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class DtoConverter {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static <D, O> D serialize(O obj, Class<D> dtoClass) {
        return modelMapper.map(obj, dtoClass);
    }

	public static <S, T> Page<T> serialize(Page<S> sourcePage, Class<T> targetClass) {
		List<T> dtoList = sourcePage.getContent().stream().map(entity -> modelMapper.map(entity, targetClass))
				.collect(Collectors.toList());
		return new PageImpl<>(dtoList, PageRequest.of(sourcePage.getNumber(), sourcePage.getSize()),
				sourcePage.getTotalElements());
	}
}
