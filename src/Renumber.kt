import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.text.DecimalFormat


// 最後の1文字をString型で取得
fun String.getLast(): String {
    return this[this.lastIndex].toString()
}

// 文字列末が「/」でなければ、末尾に付加
fun String.addSlashIfNot(): String {
    if (this.getLast() == "/")
        return this
    return this + "/"
}

fun List<File>.printName(){
    for (file in this)
        println(file.name)
}

// ディレクトリ直下のファイル一覧を取得（拡張子指定可）
fun getFilesDirectlyUnderPath(path: String, endWidth: String = "") : List<File> {
    val fileArr = File(path).listFiles()

    val listFromFirst: List<File> = if (endWidth.isNullOrEmpty())
        fileArr.toList()
    else
        fileArr.filter(
                { it.name.endsWith(endWidth) }
        )

    return listFromFirst.sorted()
}

fun toFileName(num: Int): String{
    return DecimalFormat("00000").format(num) + ".png"
}

fun copy(src: String, dst: String){
    Files.copy(
            Paths.get(src),
            Paths.get(dst))
}

fun move(src: String, dst: String){
    Files.move(
            Paths.get(src),
            Paths.get(dst))
}


fun main(args: Array<String>) {
    val pathFromFirst = "".addSlashIfNot()
    val pathFromLeftOff = "".addSlashIfNot()

    val iStart =
            getFilesDirectlyUnderPath(pathFromFirst, "png" ).size + 1
    val list =
            getFilesDirectlyUnderPath(pathFromLeftOff, "png" )
     list.printName()

    for (i in 0..list.lastIndex)
        move(
                list[i].path,
                pathFromFirst + toFileName(iStart + i)
        )
}
