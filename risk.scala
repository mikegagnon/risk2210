/**
 *
 * Simulates battles for Risk 2210 A.D.
 * @author Mike Gagnon
 *
 * Example Usage:

scala> :load risk.scala

// Roll three six-sided dice (for the attacker) against two 8-sided dice (for the defender)
scala> roll(6,6,6)(8,8)
Attacker rolls: ArrayBuffer(6, 4, 3)
Defender rolls: ArrayBuffer(6, 2)
Attacker wins 1
Defender wins 1

res7: (Int, Int) = (1,1)

// Same as a above, but do it many times and report the average.
// The average number of attacker wins is 0.768 (and 1.232 for the defender)
scala> rollTrials(6,6,6)(8,8)()
res9: (Double, Double) = (0.768,1.232)

// Executes one trial of the following scenario:
// The attacker has 11 units, two of which are commanders and thus roll 8-sided dice.
//    Denoted: (11)(8,8,6)
// The attacker is going after a series of 3 territories, one after the other:
//    Territory 1: (1, List(6)) has one normal unit (rolls a 6-sided die)
//    Territory 2: (1, List(6)) has one normal unit (rolls a 6-sided die)
//    Territory 3: (4, List(8, 6)) has three normal units and a commander. (Thus,
//                  one 8-sided die and one 6-sided die)
// The output shows that 6 attacker units make it to the final territory
scala> war(11)(8,8,6)(List((1, List(6)), (1, List(6)), (4, List(8,6))))(LoggingNo)
res1: (Int, Int) = (6,0)

// Now you can run that scenario again, but this time with logging turned on.
// (Just leave off LoggingNo)
scala> war(11)(8,8,6)(List((1, List(6)), (1, List(6)), (4, List(8,6))))()


Battle:
Attacker attacks (11)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(3, 2, 1)
Defender rolls: List(3)
Result: (0,1)

Attacker loses 1
Defender loses 0
Attacker attacks (10)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(4, 2, 1)
Defender rolls: List(6)
Result: (0,1)

Attacker loses 1
Defender loses 0
Attacker attacks (9)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(6, 5, 4)
Defender rolls: List(5)
Result: (1,0)

Attacker loses 0
Defender loses 1
Attacker wins, leaves one guy behind, and pushes everyone else forward


Battle:
Attacker attacks (8)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(7, 5, 2)
Defender rolls: List(6)
Result: (1,0)

Attacker loses 0
Defender loses 1
Attacker wins, leaves one guy behind, and pushes everyone else forward


Battle:
Attacker attacks (7)(WrappedArray(8, 8, 6)) against (4)(List(8, 6))
Attacker rolls: ArrayBuffer(7, 4, 1)
Defender rolls: List(6, 1)
Result: (2,0)

Attacker loses 0
Defender loses 2
Attacker attacks (7)(WrappedArray(8, 8, 6)) against (2)(List(8, 6))
Attacker rolls: ArrayBuffer(6, 5, 3)
Defender rolls: List(5, 5)
Result: (1,1)

Attacker loses 1
Defender loses 1
Attacker attacks (6)(WrappedArray(8, 8, 6)) against (1)(List(8, 6))
Attacker rolls: ArrayBuffer(4, 4, 1)
Defender rolls: List(5)
Result: (0,1)

Attacker loses 1
Defender loses 0
Attacker attacks (5)(WrappedArray(8, 8, 6)) against (1)(List(8))
Attacker rolls: ArrayBuffer(4, 2, 1)
Defender rolls: List(7)
Result: (0,1)

Attacker loses 1
Defender loses 0
Attacker attacks (4)(WrappedArray(8, 8, 6)) against (1)(List(8))
Attacker rolls: ArrayBuffer(7, 4, 1)
Defender rolls: List(3)
Result: (1,0)

Attacker loses 0
Defender loses 1
Attacker wins, leaves one guy behind, and pushes everyone else forward
res41: (Int, Int) = (3,0)

scala> war(11)(8,8,6)(List((1, List(6)), (1, List(6)), (4, List(8,6))))()


Battle:
Attacker attacks (11)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(6, 5, 1)
Defender rolls: List(5)
Result: (1,0)

Attacker loses 0
Defender loses 1
Attacker wins, leaves one guy behind, and pushes everyone else forward


Battle:
Attacker attacks (10)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(3, 1, 1)
Defender rolls: List(5)
Result: (0,1)

Attacker loses 1
Defender loses 0
Attacker attacks (9)(WrappedArray(8, 8, 6)) against (1)(List(6))
Attacker rolls: ArrayBuffer(7, 4, 1)
Defender rolls: List(6)
Result: (1,0)

Attacker loses 0
Defender loses 1
Attacker wins, leaves one guy behind, and pushes everyone else forward


Battle:
Attacker attacks (8)(WrappedArray(8, 8, 6)) against (4)(List(8, 6))
Attacker rolls: ArrayBuffer(5, 3, 1)
Defender rolls: List(5, 3)
Result: (0,2)

Attacker loses 2
Defender loses 0
Attacker attacks (6)(WrappedArray(8, 8, 6)) against (4)(List(8, 6))
Attacker rolls: ArrayBuffer(4, 3, 1)
Defender rolls: List(4, 2)
Result: (1,1)

Attacker loses 1
Defender loses 1
Attacker attacks (5)(WrappedArray(8, 8, 6)) against (3)(List(8, 6))
Attacker rolls: ArrayBuffer(3, 2, 1)
Defender rolls: List(5, 4)
Result: (0,2)

Attacker loses 2
Defender loses 0
Attacker attacks (3)(WrappedArray(8, 8, 6)) against (3)(List(8, 6))
Attacker rolls: ArrayBuffer(5, 4)
Defender rolls: List(6, 2)
Result: (1,1)

Attacker loses 1
Defender loses 1
Attacker attacks (2)(WrappedArray(8, 8)) against (2)(List(8, 6))
Attacker rolls: ArrayBuffer(3)
Defender rolls: List(8, 5)
Result: (0,1)

Attacker loses 1
Defender loses 0
The defender wins with 2 unit(s) remaining, in total
res42: (Int, Int) = (1,2)

 */

import scala.annotation.tailrec
import scala.util.Random

sealed abstract trait Logging
case object LoggingYes extends Logging
case object LoggingNo extends Logging

object Risk {
  def log(fn: => Unit)(implicit logging: Logging) = {
    if (logging == LoggingYes) fn
  }

  def rollDice(dice: Int*) =
    dice.map{ sides => Random.nextInt(sides) + 1 }.sortBy{ -1 * _ }

  // attacker contains the number of sides for each of the attackers dice
  // defender is analagous
  // rolls attacker and defender dice once
  // returns (a-wins, d-wins) where x-wins contains the number of dice that x won
  def roll(attacker: Int*)(defender: Int*)(implicit logging: Logging = LoggingYes) = {
    assert(attacker.size > 0 && defender.size > 0)

    val take = scala.math.min(attacker.size, defender.size)

    val attackerRolls = rollDice(attacker : _*)
    val defenderRolls = rollDice(defender : _*)
    log {
      println("Attacker rolls: %s".format(attackerRolls))
      println("Defender rolls: %s".format(defenderRolls))
    }
    val pairs = attackerRolls.take(take).zip(defenderRolls.take(take))

    val result = pairs
      .map { case (attackerRoll, defenderRoll) =>
        if (attackerRoll > defenderRoll)
          (1, 0)
        else
          (0, 1)
      }
      .reduce { (a, b) =>
        (a._1 + b._1, a._2 + b._2)
      }

    log {
      println("Attacker wins %d".format(result._1))
      println("Defender wins %d".format(result._2))
      println()
    }
    result
  }

  // one territory attacks another until the attacer has one mod, or the defender has zero
  // returns (a, d) where a is the number of attackers remaining (similar for d)
  @tailrec
  def battle(attackMods: Int)(attackDice: Int*)(defendMods: Int)(defendDice: Int*)
      (implicit logging: Logging = LoggingYes): (Int, Int) = {
    assert(attackMods >= 1 && defendMods >= 0)
    if (attackMods == 1 || defendMods == 0) {
      (attackMods, defendMods)
    } else {
      log {
        println("Attacker attacks (%s)(%s) against (%s)(%s)".format(attackMods, attackDice,
          defendMods, defendDice))
      }
      val numAttackerDice = scala.math.min(3, attackMods - 1)
      val numDefenderDice = scala.math.min(2, defendMods)
      val aDice = attackDice.sortBy{ -1 * _ }.take(numAttackerDice)
      val dDice = defendDice.sortBy{ -1 * _ }.take(numDefenderDice)
      val (attackWins, defendWins) = roll(aDice: _*)(dDice: _*)
      battle(attackMods - defendWins)(aDice : _*)(defendMods - attackWins)(dDice :_*)
    }
  }

  // an attacker goes after a series of defending countries
  // defense is a list of defending territories;
  // each defending territory is (defendMods, defendDice)
  // returns (a, d) where a is the number of attackers remaining in the last conquered territory
  //  (similar for d)
  @tailrec
  def war(attackMods: Int)(attackDice: Int*)(defense: List[(Int, List[Int])])
      (implicit logging: Logging = LoggingYes): (Int, Int) = {
    defense match {
      case Nil => {
        log {
          println("The attacker wins with %d unit(s) in the final territory".format(attackMods))
        }
        (attackMods, 0)
      }
      case (defendMods, defendDice) :: defenseTail => {
        log{ println("\n\nBattle:") }
        val (attackersRemaining, defendersRemaining) = battle(attackMods)(attackDice : _*)(defendMods)(defendDice: _*)
        if (attackersRemaining == 1) {
          val totalDefenders = defense.tail.map{ _._1 }.sum + defendersRemaining
          log {
            println("The defender wins with %d unit(s) remaining, in total".format(totalDefenders))
          }
          (attackersRemaining, totalDefenders)
        } else {
          log {
            println("Attacker wins, leaves one guy behind, and pushes everyone else forward")
          }
          war(attackersRemaining - 1)(attackDice: _*)(defenseTail)
        }
      }
    }
  }

  // attackersRemaining and defendersRemaining are only incremented when they win
  case class WarSum(numAttackerWins: Double, attackersRemaining: Double, defendersRemaining: Double) {
    def plus(that: WarSum) = WarSum(
      numAttackerWins + that.numAttackerWins,
      attackersRemaining + that.attackersRemaining,
      defendersRemaining + that.defendersRemaining)

    def average(trials: Double) = WarSum(
      numAttackerWins / trials,
      attackersRemaining / numAttackerWins,
      defendersRemaining / (trials - numAttackerWins))
  }

  def warTrials(attackMods: Int)(attackDice: Int*)(defense: List[(Int, List[Int])])(trials: Int = 1000)
      (implicit logging: Logging = LoggingNo) = {
    val sum = (0 until trials)
      .map { _ =>
        val (attackers, defenders) = war(attackMods)(attackDice: _*)(defense)
        if (defenders > 0) {
          assert(attackers == 1)
          WarSum(0.0, 0.0, defenders)
        } else {
          WarSum(1.0, attackers, 0.0)
        }
      }
      .reduce { (a, b) =>
        a.plus(b)
      }

    sum.average(trials)
  }

  // rolls attacker and defender dice trials times
  // returns (a-wins, d-wins) where x-wins is the average number of kills for x (per trial)
  def rollTrials(attacker: Int*)(defender: Int*)(trials: Int = 1000)
      (implicit logging: Logging = LoggingNo) = {

    val sum = (0 until trials)
      .map { _ =>
        roll(attacker: _*)(defender: _*)
      }
      .reduce { (a, b) =>
        (a._1 + b._1, a._2 + b._2)
      }

    (sum._1 / trials.toDouble, sum._2 / trials.toDouble)
  }
}
