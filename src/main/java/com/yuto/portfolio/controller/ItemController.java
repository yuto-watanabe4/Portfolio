package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items") // URLの始まりを /items に統一
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //一覧表示
    @GetMapping
    public String listItems(Model model) {
        //Serviceから前食材を取得してmodelに積む
        model.addAttribute("items", itemService.getAllItems());
        //templates/items/list.htmlを表示
        return "items/list";
    }


    //新規登録画面を表示
    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new Item()); //空のオブジェクトを渡す
        return "items/form";
    }

    //編集画面を表示
    @GetMapping("/edit/{id}")
    public String editItemForm(@PathVariable Integer id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "items/form"; //新規と同じフォームを使いまわす
    }

    //保存処理（新規・編集共通）
    @PostMapping("/save")
    public String saveItem(@ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/items"; //保存したら一覧に戻る
    }

    //削除処理
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }




}
