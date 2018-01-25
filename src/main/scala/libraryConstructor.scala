/*
This is a optional homework given in CS 474 OOLE.
Intention of this project is to implement type safe builder pattern in Scala
 The following implementation is intended to build a library and open it for public.
 the builder pattern make sure that all the objects are provided before the library can be open for public
 */


case class Building(structure: Seq[String])
object Constructor
{
  def main(args: Array[String]): Unit =
  {
   //calling the constructor with all the right attributes. if we fail to mention anyone the program will throw an error.
    val libraryConstructor = new Constructor[Library.EmptyLibrary](Nil).setFoundation("Built Library").addTables("Added tables of all size and type : round,square, large, and small").addBooks("Added Books : STEM books").addStaff("Hired Qualified Staff").OpenLibrary("Open for public!").build
    print(libraryConstructor)
  }
  def apply[T <: Library](structure: Seq[String]): Constructor[T] = new Constructor[T](structure)
  def apply(): Constructor[Library.EmptyLibrary] = apply[Library.EmptyLibrary](Seq())

  sealed trait Library

  /*
  Library object
   */
  object Library
  {
    sealed trait EmptyLibrary extends Library
    sealed trait Foundation extends Library
    sealed trait Tables extends Library
    sealed trait Books extends Library
    sealed trait Staff extends Library
    sealed trait Open extends Library
    type BuiltLibrary = EmptyLibrary with Foundation with Tables with Books with Staff with Open
  }


}
/*
Constructor class to build the library
 */
class Constructor[Library <: Constructor.Library] protected (structure: Seq[String])
{
  import Constructor.Library._

  def setFoundation(foundationType: String): Constructor[Library with Foundation] = Constructor(structure :+ foundationType)
//starting with the foundation
  def addTables(TablesType: String): Constructor[Library with Tables] = Constructor(structure :+ TablesType)
//adding tables
  def addBooks(BooksType: String): Constructor[Library with Books] = Constructor(structure :+ BooksType)
//adding books
  def addStaff(StaffType: String): Constructor[Library with Staff] = Constructor(structure :+ StaffType)
//adding staff
  def OpenLibrary(OpenType: String): Constructor[Library with Open] = Constructor(structure :+ OpenType)

  def build(implicit ev: Library =:= BuiltLibrary): Building = Building(structure)
}



