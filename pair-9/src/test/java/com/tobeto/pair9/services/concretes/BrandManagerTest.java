package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.exceptions.BrandBusinessException;
import com.tobeto.pair9.core.utilities.mappers.ModelMapperManager;
import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.BaseResponse;


import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.repositories.BrandRepository;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import com.tobeto.pair9.services.rules.BrandBusinessRules;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandManagerTest {
    @InjectMocks
    private BrandManager brandManager;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private BrandBusinessRules brandBusinessRules;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brandManager = new BrandManager(brandRepository,  modelMapperService,brandBusinessRules);

    }

    @Test
    void getById_itShouldReturnBrandDto() {
        // Given
        int id = 1;
        Brand brand = new Brand();
        brand.setId(id);
        GetByIdBrandResponse response = new GetByIdBrandResponse();
        response.setId(id);
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forResponse()).thenReturn(mockedModelMapper);


        when(brandRepository.findById(id))
                .thenReturn(Optional.of(brand));
        when(modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class))
                .thenReturn(response);

        // When
        BaseResponse<GetByIdBrandResponse> result = brandManager.getById(id);

        // Then
        assertTrue(result.isSuccess());
        assertEquals(response.getId(), result.getData().getId());
    }



    @Test
    void getAll_itShouldReturnListOfBrandDto() {
        List<Brand> brands = Arrays.asList(new Brand(),new Brand());
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forResponse()).thenReturn(mockedModelMapper);


        when(brandRepository.findAll()).thenReturn(brands);

        when(modelMapperService.forResponse().map(brands.get(0), GetListBrandResponse.class))
                .thenReturn(new GetListBrandResponse());

        when(modelMapperService.forResponse().map(brands.get(1), GetListBrandResponse.class))
                .thenReturn(new GetListBrandResponse());

        BaseResponse<List<GetListBrandResponse>> result = brandManager.getAll();

        assertEquals(2,result.getData().size());

    }

    @Test
    void add_ShouldAddNewBrand() {
        AddBrandRequest request = new AddBrandRequest();
        request.setName("BMW");
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(mockedModelMapper);

        when(modelMapperService.forRequest().map(request,Brand.class))
                .thenReturn(new Brand());

        BaseResponse result = brandManager.add(request);

        assertTrue(result.isSuccess());
    }

    @Test
    void update_ShouldUpdateBrand() {
        UpdateBrandRequest request = new UpdateBrandRequest();
        request.setId(3);
        request.setName("AUDI");
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(mockedModelMapper);

        when(modelMapperService.forRequest().map(request,Brand.class))
                .thenReturn(new Brand());

        BaseResponse result = brandManager.update(request);
        assertTrue(result.isSuccess());
    }

    @Test
    void delete_ShouldDeleteBrand() {
        int id = 1;

        brandManager.delete(id);

        verify(brandRepository, times(1)).deleteById(id);

    }
    @Test
    void brandNameAlreadyExistsShouldThrowException(){

        String name = "FIAT";
        BrandBusinessRules brandBusinessRules = new BrandBusinessRules(brandRepository);
        when(brandRepository.existsByName(name)).thenReturn(true);

        assertThrows(BrandBusinessException.class, () ->brandBusinessRules.isExistBrandByName(name));
        verify(brandRepository, never()).save(any());


    }

    @Test
    void brandIdNotExistShouldThrowException() {

        int id = 123;
        BrandBusinessRules brandBusinessRules = new BrandBusinessRules(brandRepository);
        when(brandRepository.existsById(id)).thenReturn(false);



        assertThrows(BrandBusinessException.class, () ->brandBusinessRules.isExistBrandById(id));

    }
    @Test
    void testExistsBrandId(){
        int id = 1;
        when(brandRepository.existsById(id)).thenReturn(true);

        boolean exists = brandManager.isExistBrandById(id);
        assertTrue(exists);
    }

}