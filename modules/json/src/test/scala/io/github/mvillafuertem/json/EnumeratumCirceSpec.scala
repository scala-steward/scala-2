package io.github.mvillafuertem.json

import enumeratum.EnumEntry.UpperSnakecase
import enumeratum.{ CirceEnum, EnumEntry, _ }
import io.circe.Decoder.Result
import io.circe._
import io.circe.syntax._
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

import scala.util.matching.Regex

final class EnumeratumCirceSpec extends AnyFlatSpecLike with Matchers {

  behavior of "EnumeratumCirce"

  it should "parse with custom decoder" in {

    // g i v e n
    sealed trait ShirtSize extends EnumEntry with UpperSnakecase

    case object ShirtSize extends Enum[ShirtSize] with CirceEnum[ShirtSize] {

      override implicit val circeDecoder: Decoder[ShirtSize] = new Decoder[ShirtSize] {
        final def apply(c: HCursor): Result[ShirtSize] = implicitly[Decoder[String]].apply(c).flatMap { s =>
          val maybeMember = withNameOption(s)
          maybeMember match {
            case Some(member) => Right(member)
            case _            => Right(UnknownSize(s))
          }
        }
      }

      case object Small extends ShirtSize

      case class UnknownSize(value: String) extends ShirtSize

      val values = findValues

    }

    // w h e n
    val small: ShirtSize       = ShirtSize.Small
    val unknownSize: ShirtSize = ShirtSize.UnknownSize("Large")

    // t h e n
    small.asJson shouldBe Json.fromString("SMALL")
    unknownSize.asJson shouldBe Json.fromString("UNKNOWN_SIZE(LARGE)")

    Json.fromString("Large").as[ShirtSize] shouldBe Right(unknownSize)
    Json.fromString("SMALL").as[ShirtSize] shouldBe Right(small)

  }

  it should "parse with custom decoder and add pepe extra characters" in {

    // g i v e n
    trait PepeExtraCharacters extends EnumEntry {

      private val regex: Regex = "(?<=\\()[^)]+(?=\\))".r

      override def entryName: String = stableEntryName

      private[this] lazy val stableEntryName: String = regex
        .findFirstIn(super.entryName)
        .map(s => s"UnknownSize$s")
        .getOrElse(super.entryName)

    }

    sealed trait ShirtSize extends EnumEntry with PepeExtraCharacters with UpperSnakecase

    case object ShirtSize extends Enum[ShirtSize] with CirceEnum[ShirtSize] {

      override implicit val circeDecoder: Decoder[ShirtSize] = new Decoder[ShirtSize] {
        final def apply(c: HCursor): Result[ShirtSize] = implicitly[Decoder[String]].apply(c).flatMap { s =>
          val maybeMember = withNameOption(s)
          maybeMember match {
            case Some(member) => Right(member)
            case _            => Right(UnknownSize(s))
          }
        }
      }

      case object Small extends ShirtSize

      case class UnknownSize(value: String) extends ShirtSize

      val values = findValues

    }

    // w h e n
    val small: ShirtSize       = ShirtSize.Small
    val unknownSize: ShirtSize = ShirtSize.UnknownSize("Large")

    // t h e n
    small.asJson shouldBe Json.fromString("SMALL")
    unknownSize.asJson shouldBe Json.fromString("UNKNOWN_SIZE_LARGE")

    Json.fromString("Large").as[ShirtSize] shouldBe Right(unknownSize)
    Json.fromString("SMALL").as[ShirtSize] shouldBe Right(small)

  }

}

object EnumeratumCirceSpec {}
