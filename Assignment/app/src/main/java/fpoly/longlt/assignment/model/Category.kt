package fpoly.longlt.assignment.model

class Category(id: Int, img: Int, category: String) {
    private var id: Int = 0
    private var img: Int = 0
    private var category: String? = null

    init {
        this.category = category
        this.img = img
        this.id = id
    }

    fun getId(): Int {
        return this.id
    }

    fun getImg(): Int {
        return this.img
    }

    fun getCategory(): String? {
        return this.category
    }
}
