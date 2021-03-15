package Model;


public enum Category {
    LYRIK("Lyrik"),
    DRAMATIK("Dramatik"),
    SKÖNLITTERATUR("Skönlitteratur"),
    ROMANNER("Romanner"),
    NOVELL("Novell"),
    DECKARE("Deckare"),
    SCIENCE_FICTION("Science fiction"),
    FANTASY("Fantasy"),
    ESSÄER("Essäer");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    /**
     * To get Enum value from constructor arguments value
     * @param categoryName Lyrik
     * @return LYRIK
     */
    public static Category getByStringCategoryName(String categoryName) {
        for (Category category : Category.values()) {
            if (category.getCategory().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }
}
