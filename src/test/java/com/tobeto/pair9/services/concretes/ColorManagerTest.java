package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.exceptions.ColorBusinessException;
import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.BaseResponse;


import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.entities.concretes.Color;
import com.tobeto.pair9.repositories.ColorRepository;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
import com.tobeto.pair9.services.rules.ColorBusinessRules;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColorManagerTest {
    @InjectMocks
    private ColorManager colorManager;
    @Mock
    private ColorRepository colorRepository;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private ColorBusinessRules colorBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        colorManager = new ColorManager(colorRepository,modelMapperService,colorBusinessRules);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll_itShouldReturnListOfColorDto(){
        List<Color> colors = Arrays.asList(new Color(),new Color());
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forResponse()).thenReturn(mockedModelMapper);


        when(colorRepository.findAll()).thenReturn(colors);

        when(modelMapperService.forResponse().map(colors.get(0), GetListColorResponse.class))
                .thenReturn(new GetListColorResponse());

        when(modelMapperService.forResponse().map(colors.get(1), GetListColorResponse.class))
                .thenReturn(new GetListColorResponse());

     BaseResponse<List<GetListColorResponse>> result = colorManager.getAll();

        assertEquals(2,result.getData().size());
    }

    @Test
    void add_ShouldAddNewColor() {
        AddColorRequest request = new AddColorRequest();
        request.setName("Red");
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(mockedModelMapper);


        when(modelMapperService.forRequest().map(request,Color.class))
                .thenReturn(new Color());

        BaseResponse result = colorManager.add(request);

        assertTrue(result.isSuccess());
    }

    @Test
    void update_ShouldUpdateColor() {
        UpdateColorRequest request = new UpdateColorRequest();
        request.setId(1);
        request.setName("Yellow");
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(mockedModelMapper);

        when(modelMapperService.forRequest().map(request,Color.class))
                .thenReturn(new Color());

      BaseResponse result = colorManager.update(request);

        assertTrue(result.isSuccess());
    }


    @Test
    void  delete_ShouldDeleteColor() {
        int id = 1 ;
        colorManager.delete(id);
        verify(colorRepository, times(1)).deleteById(id);
    }

    @Test
    void colorNameAlreadyExistsShouldThrowException(){
        String name = "Green";
    ColorBusinessRules colorBusinessRules = new ColorBusinessRules(colorRepository);
        when(colorRepository.existsByName(name))
                .thenReturn(true);

        assertThrows(ColorBusinessException.class,()->colorBusinessRules.isExistColorByName(name));
        verify(colorRepository, never()).save(any());

    }
    @Test
    void colorIdNotExistUpdateMethodShouldThrowException(){

       int id = 2;
        ColorBusinessRules colorBusinessRules = new ColorBusinessRules(colorRepository);

        when(colorRepository.existsById(id))
                .thenReturn(false);

        assertThrows(ColorBusinessException.class,()->colorBusinessRules.isExistColorById(id));

    }
    @Test
    void testExistsColorId(){
        int id = 1;
        when(colorRepository.existsById(id)).thenReturn(true);

        boolean exists = colorManager.isExistColorById(id);
        assertTrue(exists);
    }
}