package fpoly.longlt.lab

fun Bai1(){
    print("Nguyễn Văn Anh - PS123456\n")
    print("=========================\n")
    println("Quanh năm buôn bán ở mom sông")
    println("Nuôi đủ năm con với một chồng")
    println("\tlặn lội thân cờ khi quãng vắng")
    println("\teo sèo mặt nước buổi đò đồng")
    println("Một duyên hai nợ âu đành phận")
    println("Năm nắng mười mưa há chẳng công")
    println("\tCha mẹ thói đời \"ăn ở bạc\"")
    println("\tCó chồng hờ hững cũng như không")
}

fun Bai2() {
    var a = 0.0;
    var b = 0.0;
    println("nhập a: ");
    a = readLine()!!.toDouble();
    println("nhập b: ")
    b = readLine()!!.toDouble();
    println("a+b = "+(a+b));
    println("a-b = "+(a-b));
    println("a*b = "+(a*b));
    println("a/b = "+(a/b));
    println("a%b = "+(a%b));
}

fun main(){
    println("Bai 1:");
    Bai1();
    println("Bai 2:");
    Bai2()
}