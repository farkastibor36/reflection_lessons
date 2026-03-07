import org.example.dto.ItemDto;
import org.example.mapper.ItemMapper;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.service.ItemCRUDService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemCRUDServiceTest {
    @Mock
    ItemRepository itemRepository;
    @Mock
    ItemMapper itemMapper;

    @InjectMocks
    ItemCRUDService itemCRUDService;

    @Test
    void save() {
        Item input = new Item();
        Item saved = new Item();
        ItemDto dto = new ItemDto("Test", 1, 100, "Leírás");
        when(itemMapper.toDto(saved)).thenReturn(dto);
        when(itemRepository.save(input)).thenReturn(saved);

        Optional<ItemDto> result = itemCRUDService.save(input);

        assertTrue(result.isPresent());
        assertSame(dto, result.get());

        verify(itemRepository).save(input);
        verify(itemMapper).toDto(saved);
        verifyNoMoreInteractions(itemRepository, itemMapper);
    }

    @Test
    void findAll() {
        Item a = new Item();
        Item b = new Item();
        ItemDto da = new ItemDto("Test", 1, 100, "Leírás");
        ItemDto db = new ItemDto("Test2", 2, 200, "Leírás2");

        when(itemRepository.findAll()).thenReturn(java.util.List.of(a, b));
        when(itemMapper.toDto(a)).thenReturn(da);
        when(itemMapper.toDto(b)).thenReturn(db);
        List<ItemDto> result = itemCRUDService.findAllDto();

        assertEquals(List.of(da, db), result);

        verify(itemRepository).findAll();
        verify(itemMapper).toDto(a);
        verify(itemMapper).toDto(b);
        verifyNoMoreInteractions(itemRepository, itemMapper);
    }

    @Test
    void delete() {
        itemCRUDService.deleteById(1L);

        verify(itemRepository).deleteById(1L);
        verifyNoMoreInteractions(itemRepository, itemMapper);
    }

    @Test
    void findById() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> itemCRUDService.findById(99L));

        verify(itemRepository).findById(99L);
        verifyNoMoreInteractions(itemRepository, itemMapper);
    }
}
