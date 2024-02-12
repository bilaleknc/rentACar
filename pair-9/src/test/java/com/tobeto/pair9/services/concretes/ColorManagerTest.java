package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.entities.concretes.Color;
import com.tobeto.pair9.repositories.ColorRepository;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import com.tobeto.pair9.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair9.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair9.services.dtos.color.responses.GetListColorResponse;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ColorManager colorManager = new ColorManager(colorRepository,modelMapperService);
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

        DataResult<List<GetListColorResponse>> result = colorManager.getAll();

        assertEquals(2,result.getData().size());
    }

    @Test
    void add_ShouldAddNewBrand() {
        AddColorRequest request = new AddColorRequest();
        request.setName("Red");
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(mockedModelMapper);

        when(modelMapperService.forRequest().map(request,Color.class))
                .thenReturn(new Color());

        Result result = colorManager.add(request);

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

        Result result = colorManager.update(request);

        assertTrue(result.isSuccess());
    }


    @Test
    void  delete_ShouldDeleteColor() {
        int id = 1 ;
        colorManager.delete(id);
        verify(colorRepository, times(1)).deleteById(id);
    }

    @Test
    void colorNameAlreadyExistsAddMethodShouldThrowException(){
        AddColorRequest request = new AddColorRequest();
        request.setName("Green");
        Brand brand = new Brand();
        brand.setName("Green");

        when(colorRepository.existsByName(request.getName()))
                .thenReturn(true);

        assertThrows(RuntimeException.class,()->colorManager.add(request));
        verify(colorRepository, never()).save(any());

    }
    @Test
    void colorNameAlreadyExistsUpdateMethodShouldThrowException(){
        UpdateColorRequest request = new UpdateColorRequest();
        request.setName("Red");


        when(colorRepository.existsByName(request.getName()))
                .thenReturn(true);

        assertThrows(RuntimeException.class,()->colorManager.update(request));

    }
    @Test
    void testExistsColorId(){
        int id = 1;
        when(colorRepository.existsById(id)).thenReturn(true);

        boolean exists = colorManager.existsId(id);
        assertTrue(exists);
    }
}