import scala.annotation.tailrec
import scala.util.Random

def rollDice(dice: Int*) =
  dice.map{ sides => Random.nextInt(sides) + 1 }.sortBy{ -1 * _ }

// attacker contains the number of sides for each of the attackers dice
// defender is analagous
// rolls attacker and defender dice once
// returns (a-wins, d-wins) where x-wins contains the number of dice that x won
def roll(attacker: Int*)(defender: Int*) = {
  assert(attacker.size > 0 && defender.size > 0)

  val take = scala.math.min(attacker.size, defender.size)

  val attackerRolls = rollDice(attacker : _*)
  val defenderRolls = rollDice(defender : _*)
  //println("Attacker rolls: %s".format(attackerRolls))
  //println("Defender rolls: %s".format(defenderRolls))
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

  //println("Result: %s".format(result))
  //println()
  result
}

// one territory attacks another until the attacer has one mod, or the defender has zero
// returns (a, d) where a is the number of attackers remaining (similar for d)
@tailrec
def battle(attackMods: Int)(attackDice: Int*)(defendMods: Int)(defendDice: Int*): (Int, Int) = {
  assert(attackMods >= 1 && defendMods >= 0)
  if (attackMods == 1 || defendMods == 0) {
    (attackMods, defendMods)
  } else {
    //println("Attacker attacks (%s)(%s) against (%s)(%s)".format(attackMods, attackDice,
    //  defendMods, defendDice))
    val numAttackerDice = scala.math.min(3, attackMods - 1)
    val numDefenderDice = scala.math.min(2, defendMods)
    val aDice = attackDice.sortBy{ -1 * _ }.take(numAttackerDice)
    val dDice = defendDice.sortBy{ -1 * _ }.take(numDefenderDice)
    val (attackWins, defendWins) = roll(aDice: _*)(dDice: _*)
    //println("Attacker loses %d".format(defendWins))
    //println("Defender loses %d".format(attackWins))
    battle(attackMods - defendWins)(aDice : _*)(defendMods - attackWins)(dDice :_*)
  }
}

// an attacker goes after a series of defending countries
// defense is a list of defending territories;
// each defending territory is (defendMods, defendDice)
// returns (a, d) where a is the number of attackers remaining in the last conquered territory
//  (similar for d)
@tailrec
def war(attackMods: Int)(attackDice: Int*)(defense: List[(Int, List[Int])]): (Int, Int) = {
  defense match {
    case Nil => (attackMods, 0)
    case (defendMods, defendDice) :: defenseTail => {
      //println("\n\nBattle:")
      val (attackersRemaining, defendersRemaining) = battle(attackMods)(attackDice : _*)(defendMods)(defendDice: _*)
      if (attackersRemaining == 1) {
        val totalDefenders = defense.tail.map{ _._1 }.sum + defendersRemaining
        (attackersRemaining, totalDefenders)
      } else {
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

def warTrials(attackMods: Int)(attackDice: Int*)(defense: List[(Int, List[Int])])(trials: Int = 1000) = {
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
def rollTrials(attacker: Int*)(defender: Int*)(trials: Int = 1000) = {

  val sum = (0 until trials)
    .map { _ =>
      roll(attacker: _*)(defender: _*)
    }
    .reduce { (a, b) =>
      (a._1 + b._1, a._2 + b._2)
    }

  (sum._1 / trials.toDouble, sum._2 / trials.toDouble)
}