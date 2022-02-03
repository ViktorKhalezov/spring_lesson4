package ru.geekbrains.spring_lesson41;


import lombok.*;

    @Builder
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Product {
        private Integer id;
        private String title;
        private Double cost;


        @Override
        public String toString() {
            return "Product {" +
                    "id = " + id +
                    ", title = '" + title + '\'' +
                    ", price = " + cost +
                    '}';
        }

    }


