import org.example.controller.ItemController;
import org.example.dto.ItemDto;
import org.example.mapper.ItemMapper;
import org.example.model.Item;
import org.example.service.ItemCRUDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ItemControllerTest {
    MockMvc mockMvc;

    ItemCRUDService itemCRUDService = Mockito.mock(ItemCRUDService.class);
    ItemMapper itemMapper = Mockito.mock(ItemMapper.class);

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ItemController(itemCRUDService, itemMapper)).build();
    }

    @Test
    void getAllItems() throws Exception {
        when(itemCRUDService.findAllDto()).thenReturn(List.of(new ItemDto("car", 2, 250000, "car description")));

        mockMvc.perform(get("/items/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].name", is("car")))
                .andExpect(jsonPath("$[0].quantity").value(2))
                .andExpect(jsonPath("$[0].price", is(250000)))
                .andExpect(jsonPath("$[0].description", is("car description")));

        verify(itemCRUDService).findAllDto();
        verifyNoMoreInteractions(itemCRUDService, itemMapper);
    }

    @Test
    void getItemById() throws Exception {
        when(itemCRUDService.findById(1L)).thenReturn(Optional.of(new ItemDto("car", 2, 250000, "car description")));

        mockMvc.perform(get("/items/1", 1L))
                .andExpect(status().isOk());
        verify(itemCRUDService).findById(1L);
        verifyNoMoreInteractions(itemCRUDService, itemMapper);
    }

    @Test
    void createItem() throws Exception {
        ItemDto savedDto = new ItemDto("car", 2, 250000, "car description");
        when(itemCRUDService.save(any(Item.class))).thenReturn(Optional.of(savedDto));

        mockMvc.perform(post("/items/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "car",
                                  "quantity": 2,
                                  "price": 250000,
                                  "description": "car description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("car")))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.price", is(250000)))
                .andExpect(jsonPath("$.description", is("car description")));

        verify(itemCRUDService).save(any(Item.class));
        verifyNoMoreInteractions(itemCRUDService, itemMapper);
    }

    @Test
    void updateItem() throws Exception {
        Item updatedItem = new Item();
        ItemDto updatedDto = new ItemDto("car", 2, 250000, "car description");

        when(itemCRUDService.update(eq(1L), any(ItemDto.class))).thenReturn(updatedItem);
        when(itemMapper.toDto(updatedItem)).thenReturn(updatedDto);

        mockMvc.perform(put("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "car",
                                  "quantity": 2,
                                  "price": 250000,
                                  "description": "car description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("car")))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.price", is(250000)))
                .andExpect(jsonPath("$.description", is("car description")));

        verify(itemCRUDService).update(eq(1L), any(ItemDto.class));
        verify(itemMapper).toDto(updatedItem);
        verifyNoMoreInteractions(itemCRUDService, itemMapper);
    }

    @Test
    void partialUpdateItem() throws Exception {
        Item updatedItem = new Item();
        ItemDto updatedDto = new ItemDto("car", 2, 250000, "car description");

        when(itemCRUDService.partialUpdate(eq(1L), any(ItemDto.class))).thenReturn(updatedItem);
        when(itemMapper.toDto(updatedItem)).thenReturn(updatedDto);

        mockMvc.perform(patch("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "car description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("car")))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.price", is(250000)))
                .andExpect(jsonPath("$.description", is("car description")));

        verify(itemCRUDService).partialUpdate(eq(1L), any(ItemDto.class));
        verify(itemMapper).toDto(updatedItem);
        verifyNoMoreInteractions(itemCRUDService, itemMapper);
    }

    @Test
    void deleteItem() throws Exception {
        mockMvc.perform(delete("/items/1", 1L))
                .andExpect(status().isNoContent());
        verify(itemCRUDService).deleteById(1L);
        verifyNoMoreInteractions(itemCRUDService, itemMapper);
    }
}
