package com.yuto.portfolio.controller;

import com.yuto.portfolio.entity.Item;
import com.yuto.portfolio.repository.CategoryRepository;
import com.yuto.portfolio.repository.ItemRepository;
import com.yuto.portfolio.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public String listItems(Model model) {
        //前食材取得
        model.addAttribute("items", itemService.getAllItems());
        //在庫不足の食材だけを取得してモデルに渡す
        model.addAttribute("lowStockItems", itemService.getLowStockItems());

        return "items/list";
    }

    //登録画面を表示する
    @GetMapping("/items/new")
    public String newItem(Model model) {
        //カテゴリ一覧をDBから取得してHTMLに渡す
        model.addAttribute("categories", categoryRepository.findAll());
        return "items/register";//register.htmlを表示
    }

    //保存ボタンが押された時の処理
    @PostMapping("items/register")
    public String createItem(Item item) {
        //画面から送られてきたitemをService軽油でDBに保存
        itemService.saveItem(item);
        //保存が終ったら、;一覧画面(/items)に自動で戻る
        return "redirect:/items";
    }

    //編集画面を表示
    @GetMapping("/items/edit/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        //URLの{id}を使って、修正したい食材を1件検索
        Item item = itemService.getAllItems().stream().filter(i -> i.getId().equals(id)).findFirst().orElseThrow();

        model.addAttribute("item", item);
        return "items/edit"; //edit.htmlを表示
    }

    //更新処理
    @PostMapping("/items/update")
    public String updateItem(Item item) {
        itemService.saveItem(item); //IDが含まれていれば、JPAが自動で更新だと判断する
        return "redirect:/items";
    }

    //削除処理
    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        return "redirect:/items";
    }

    //入出庫画面表示
    @GetMapping("/items/stock/{id}")
    public String showStockForm(@PathVariable Long id, Model model) {
        Item item = itemService.getAllItems().stream().filter(i -> i.getId().equals(id)).findFirst().orElseThrow();
        model.addAttribute("item", item);
        return "items/stock_form"; //stock_form.html表示
    }

    //入出庫を実行
    @PostMapping("items/stock/update")
    public String updateStock(@RequestParam Long itemId, @RequestParam Integer amount, @RequestParam String type) {
        //ユーザーＩＤはいったん１に固定
        itemService.updateStockWithLog(itemId, amount, type, 1L);
        return "redirect:/items";
    }

    @PostMapping("/save")
    public String saveItem(@Validated @ModelAttribute Item item, BindingResult result) {
        // もし入力エラーがあったら、元の画面に戻す
        if (result.hasErrors()) {
            return "items/form";
        }
        itemRepository.save(item);
        return "redirect:/items";
    }

}
