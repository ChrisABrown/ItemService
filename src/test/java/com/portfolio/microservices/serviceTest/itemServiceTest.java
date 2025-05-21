package com.portfolio.microservices.serviceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

import com.portfolio.itemservice.domain.ItemDomain;
import com.portfolio.itemservice.repository.ItemRepository;
import com.portfolio.itemservice.service.ItemService;
import com.portfolio.itemservice.suprimeapi.model.Item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class itemServiceTest {

    @Mock
    ItemRepository itemRepository;
    ItemDomain mockItem = new ItemDomain();

    @InjectMocks
    ItemService itemService = new ItemService(itemRepository);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(itemServiceTest.class);
        // Initialize the mockItem with a UUID
        UUID itemId = UUID.randomUUID();
        mockItem.setItemDomainId(itemId);
    }

    private Item mapItemDomainToModel(ItemDomain itemDomain) {
        if (itemDomain == null) {
            return null; // Defensive coding to avoid NPE
        }
        Item item = new Item();
        ItemDomain wrapper = new ItemDomain(
                itemDomain.getSKU(),
                itemDomain.getCategory(),
                itemDomain.getColor(),
                itemDomain.getDescription(),
                itemDomain.getId(),
                itemDomain.getImage(),
                itemDomain.getItemDomainId(),
                itemDomain.getItemName(),
                itemDomain.getPrice(),
                itemDomain.getSize(),
                itemDomain.getStock()
        );
        item.setItemId(wrapper.getItemDomainId());
        item.setItemName(wrapper.getItemName());
        item.setCategory(wrapper.getCategory());
        item.setSKU(wrapper.getSKU());
        item.setPrice(wrapper.getPrice());
        item.setDescription(wrapper.getDescription());
        item.setImage(wrapper.getImage());
        item.setSize(wrapper.getSize());
        item.setColor(wrapper.getColor());
        item.setStock(wrapper.getStock());
        return item;
    }

    @Test
    void testCreateItem() {
        @NotNull
        @Valid
        UUID itemId = UUID.randomUUID();
        List<String> sizes = new ArrayList<>();
        Collections.addAll(sizes, "S", "M", "L", "XL", "XXL");
        ItemDomain savedDomain = new ItemDomain(
                "SKU-001",
                "Category A",
                null,
                "A description",
                null,
                "image.jpg",
                itemId,
                "Test Item",
                99.99,
                sizes,
                50
        );
        System.out.println("Item ID2: " + savedDomain.getItemDomainId());
        Item result = mapItemDomainToModel(savedDomain);
        when(itemRepository.findByItemId(itemId)).thenReturn(savedDomain);
        when(itemRepository.save(Mockito.any(ItemDomain.class))).thenReturn(savedDomain);
        itemService.createItem(result);
        assertNotNull(result);
        assertEquals(itemId, result.getItemId());
        assertEquals("Test Item", result.getItemName());
        verify(itemRepository, times(1)).insert(Mockito.any(ItemDomain.class));
    }

    @Test
    void testGetAllItems() {
        ItemDomain item1 = new ItemDomain();
        item1.setItemDomainId(UUID.randomUUID());
        item1.setItemName("Item 1");
        item1.setCategory("Category A");
        ItemDomain item2 = new ItemDomain();
        item2.setItemDomainId(UUID.randomUUID());
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
        UUID itemId = mockItem.getItemDomainId();
        String blank = itemId.toString();
        when(itemRepository.findByItemId(mockItem.getItemDomainId())).thenReturn(mockItem);
        Item result = itemService.getItem(mockItem.getItemDomainId());
        assertNotNull(result);
        UUID idString = UUID.fromString(blank);
        assertEquals(itemId, result.getItemId());
        verify(itemRepository, times(1)).findByItemId(idString);
    }

    @Test
    void testDeleteItem() {
        UUID itemId = mockItem.getItemDomainId();
        when(itemRepository.findByItemId(itemId)).thenReturn(mockItem);
        itemService.deleteItem(itemId);
        verify(itemRepository, times(1)).delete(mockItem);
    }

    @Test
    void testUpdateItem() {
        UUID itemId = UUID.randomUUID();
        mockItem.setItemDomainId(itemId);
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
