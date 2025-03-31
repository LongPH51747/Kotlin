package fpoly.longlt.lab


fun main() {
    println("Bài 1:")
    bai1();
    println("Bài 2:")
    bai2();
    println("Bài 3:")
    bai3();
}

fun bai1() {
    var a = 0.0;
    var b = 0.0;
    println("mời nhập a:")
    a = readLine()!!.toDouble();
    println("mời nhập b:")
    b = readLine()!!.toDouble();
    if (a === 0.0 && b === 0.0) {
        println("phương trình vô số nghiệm")
    } else if (a === 0.0) {
        println("phương trình vô nghiệm")
    } else {
        var c = (-b / a);
        println("phương trình có nghiệm x = " + c)
    }

}

fun bai2() {
    var m = 0;
    print("nhập 1 tháng bất kì:")
    m = readLine()!!.toInt();
    when (m) {
        1, 2, 3 -> println("tháng " + m + " là quý 1");
        4, 5, 6 -> println("tháng " + m + " là quý 2");
        7, 8, 9 -> println("tháng " + m + " là quý 3");
        10, 11, 12 -> println("tháng " + m + " là quý 4");
        else -> println("chỉ có tháng từ 1 - 12");
    }
}

fun bai3() {
    var y = 0;
    var s: String?;
    do {
        print("nhập năm bất kì: ")
        y = readLine()!!.toInt();
        while (y <= 0) {
            println("nhập lại năm lớn hơn 0")
            y = readLine()!!.toInt()
        }
        if (y % 4 === 0 && y % 100 !== 0 || y % 400 === 0) {
            println("năm " + y + " là năm nhuận")
        } else {
            println("năm " + y + " không là năm nhuận")
        }
        println("có tiếp tục không?\n Y/N")
        s = readLine()
        if (s.equals("N",true)){
            break;
        }
    } while (true)
}
