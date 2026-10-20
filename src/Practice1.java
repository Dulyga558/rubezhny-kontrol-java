public class Practice1 {
    public static void main(String[] args) {
        // Этап 4. Мини-задача «Чек покупки» - Дано:
        String product = "Coffee";
        int quantity = 3;
        double price = 1250.0;
        double discountPercent = 10.0;

        // Вычисления (Формулалар арқылы есептеу)
        double totalWithoutDiscount = quantity * price;
        double discountAmount = totalWithoutDiscount * (discountPercent / 100.0);
        double finalPrice = totalWithoutDiscount - discountAmount;

        // Вывод результатов
        System.out.println("=== ЧЕК ПОКУПКИ ===");
        System.out.println("Наименование товара: " + product);
        System.out.println("Количество: " + quantity);
        System.out.println("Стоимость без скидки: " + totalWithoutDiscount + " тг");
        System.out.println("Сумма скидки (" + discountPercent + "%): " + discountAmount + " тг");
        System.out.println("Итоговая стоимость: " + finalPrice + " тг");

        /*
         * Пояснения к выполнению Задания «Чек покупки»:
         * 1. Стоимость без скидки вычисляется путем умножения количества на цену товара (quantity * price).
         * 2. Сумма скидки находится как процент от стоимости без скидки (totalWithoutDiscount * (discountPercent / 100.0)).
         * 3. Итоговая стоимость рассчитывается вычитанием суммы скидки из стоимости без скидки (totalWithoutDiscount - discountAmount).
         * 4. Все итоговые числа вычислены автоматически выражениями без жесткого ручного ввода чисел.
         */
    }
}