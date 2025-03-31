package fpoly.longlt.assignment.model

class Notifi(id: Int, nameSP: String, order: String, image: Int) {
    private var id: Int = 0;
    private var nameSP: String? = null;
    private var order: String? = null;
    private var image: Int = 0;

    init {
        this.id = id;
        this.nameSP = nameSP;
        this.order = order;
        this.image = image;
    }

    public fun getId(): Int {
        return id;
    }

    public fun getNameSP(): String? {
        return nameSP;
    }

    public fun getOrder(): String? {
        return order;
    }

    public fun getImage(): Int {
        return image;
    }

}