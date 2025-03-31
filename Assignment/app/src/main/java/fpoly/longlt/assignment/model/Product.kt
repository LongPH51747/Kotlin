package fpoly.longlt.assignment.model

class Product(id: Int, name: String, price: Double, img: Int, description: String) {
    private var id: Int = 0
    private var name: String? = null
    private var price: Double = 0.0
    private var img: Int = 0
    private var description: String? = null

    init {
        this.id = id
        this.name = name
        this.price = price
        this.img = img
        this.description = description
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPrice(): Double {
        return price
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun getImg(): Int {
        return img
    }

    fun setImg(img: Int) {
        this.img = img
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }
}