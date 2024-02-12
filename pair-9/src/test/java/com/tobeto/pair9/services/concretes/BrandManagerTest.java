package com.tobeto.pair9.services.concretes;

import com.tobeto.pair9.core.utilities.mappers.ModelMapperManager;
import com.tobeto.pair9.core.utilities.mappers.ModelMapperService;
import com.tobeto.pair9.core.utilities.results.DataResult;
import com.tobeto.pair9.core.utilities.results.Result;
import com.tobeto.pair9.entities.concretes.Brand;
import com.tobeto.pair9.repositories.BrandRepository;
import com.tobeto.pair9.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair9.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair9.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair9.services.dtos.brand.responses.GetListBrandResponse;
import org.junit.jupiter.api.AfterEach;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brandManager = new BrandManager(brandRepository,  modelMapperService);

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
        DataResult<GetByIdBrandResponse> result = brandManager.getById(id);

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

        DataResult<List<GetListBrandResponse>> result = brandManager.getAll();

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

        Result result = brandManager.add(request);

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

        Result result = brandManager.update(request);
        assertTrue(result.isSuccess());
    }

    @Test
    void delete_ShouldDeleteBrand() {
        int id = 1;

        brandManager.delete(id);

        verify(brandRepository, times(1)).deleteById(id);

    }
    @Test
    void brandNameAlreadyExistsAddMethodShouldThrowException(){
        AddBrandRequest request = new AddBrandRequest();
        request.setName("FIAT");
        Brand brand = new Brand();
        brand.setName("FIAT");

        when(brandRepository.existsByName(request.getName()))
                .thenReturn(true);

        assertThrows(RuntimeException.class,()->brandManager.add(request));
        verify(brandRepository, never()).save(any());

    }
    @Test
    void brandNameAlreadyExistsUpdateMethodShouldThrowException(){
        UpdateBrandRequest request = new UpdateBrandRequest();
        request.setName("FIAT");


        when(brandRepository.existsByName(request.getName()))
                .thenReturn(true);

        assertThrows(RuntimeException.class,()->brandManager.update(request));

    }
}