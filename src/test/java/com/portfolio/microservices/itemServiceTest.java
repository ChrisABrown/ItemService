package com.portfolio.microservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.portfolio.microservices.domain.ItemDomain;
import com.portfolio.microservices.repository.ItemRepository;
import com.portfolio.microservices.service.ItemService;
import com.portfolio.microservices.suprimeapi.model.Item;

public class itemServiceTest {

    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    ItemService itemService;
    private ItemDomain mockItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockItem = new ItemDomain();
        UUID itemId = UUID.randomUUID();
        mockItem.setItemId(itemId);
    }

    private Item mapItemDomainToModel(ItemDomain itemDomain) {
        if (itemDomain == null) {
            return null; // Defensive coding to avoid NPE
        }
        Item item = new Item();
        item.setItemId(itemDomain.getItemId());
        item.setCategory(itemDomain.getCategory());
        item.setDescription(itemDomain.getDescription());
        item.setImage(itemDomain.getImage());
        item.setItemName(itemDomain.getItemName());
        item.setPrice(itemDomain.getPrice());
        item.setSKU(itemDomain.getSKU());
        item.setSize(itemDomain.getSize());
        return item;
    }

    @Test
    void testCreateItem() {
        @NotNull
        @Valid
        UUID itemId = UUID.randomUUID();
        List<String> sizes = new ArrayList<>();
        Collections.addAll(sizes, "S", "M", "L", "XL", "XXL");

        ItemDomain savedDomain = new ItemDomain();
        savedDomain.setItemId(itemId);
        savedDomain.setItemName("Test Item");
        savedDomain.setCategory("Category A");
        savedDomain.setDescription("A description");
        savedDomain.setImage("image.jpg");
        savedDomain.setPrice(99.99);
        savedDomain.setSKU("SKU-001");
        savedDomain.setSize(sizes);
        savedDomain.setStock(50);

        System.out.println("Item ID2: " + savedDomain.getItemId());
        Item result = mapItemDomainToModel(savedDomain);

        itemService.createItem(result);

        when(itemRepository.findByItemId(itemId)).thenReturn(savedDomain);
        when(itemRepository.save(Mockito.any(ItemDomain.class))).thenReturn(savedDomain);

        assertNotNull(result);
        assertEquals(itemId, result.getItemId());
        assertEquals("Test Item", result.getItemName());
        verify(itemRepository, times(1)).insert(Mockito.any(ItemDomain.class));
    }

    @Test
    void testGetAllItems() {
        ItemDomain item1 = new ItemDomain();
        item1.setItemId(UUID.randomUUID());
        item1.setItemName("Item 1");
        item1.setCategory("Category A");

        ItemDomain item2 = new ItemDomain();
        item2.setItemId(UUID.randomUUID());
        item2.setItemName("Item 2");
        item2.setCategory("Category B");

        List<ItemDomain> items = Arrays.asList(item1, item2);
        when(itemRepository.findAll()).thenReturn(items);

        List<Item> result = itemService.getAllItems();

        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAll();

    }

    @Test
    void testGetItemByItemId() {
        UUID itemId = mockItem.getItemId();

        String blank = itemId.toString();

        when(itemRepository.findByItemId(mockItem.getItemId())).thenReturn(mockItem);
        Item result = itemService.getItem(mockItem.getItemId());
        assertNotNull(result);
        UUID idString = UUID.fromString(blank);
        assertEquals(itemId, result.getItemId());
        verify(itemRepository, times(1)).findByItemId(idString);
    }

    @Test
    void testDeleteItem() {
        UUID itemId = mockItem.getItemId();
        when(itemRepository.findByItemId(itemId)).thenReturn(mockItem);
        itemService.deleteItem(itemId);
        verify(itemRepository, times(1)).delete(mockItem);
    }

    @Test
    void testUpdateItem() {
        UUID itemId = UUID.randomUUID();

        mockItem.setItemId(itemId);
        mockItem.setItemName("Old Item");
        mockItem.setCategory("Old Category");

        when(itemRepository.findByItemId(itemId)).thenReturn(mockItem);
        when(itemRepository.insert(Mockito.any(ItemDomain.class))).thenReturn(mockItem);

        Item itemUpdate = new Item();
        itemUpdate.setItemId(itemId);
        itemUpdate.setItemName("Updated Item");
        itemUpdate.setCategory("Updated Category");
        itemUpdate.setDescription("Updated Description");
        itemUpdate.setImage("image.png");
        itemUpdate.setPrice(49.99);
        itemUpdate.setSKU("SKU999");
        itemUpdate.setSize(List.of("L", "XL"));

        Item result = itemService.updateItem(itemId, itemUpdate);
        assertNotNull(result);
        assertEquals("Updated Item", result.getItemName());

        verify(itemRepository).findByItemId(itemId);
        verify(itemRepository).insert(Mockito.any(ItemDomain.class));
    }
    // test.testFindById();
    // test.testFindAll();
    // test.testDeleteById();
    // test.testUpdate();
    // test.testCount();
    // test.testExistsById();
    // test.testFindByName();
    // test.testFindByPriceRange();
    // test.testFindByStockGreaterThan();
    // test.testFindByCategory();
    // test.testFindBySKU();
    // test.testFindByItemId();
}
