module Blackjack
  def self.parse_card(card)
    case card
    when "ace" then 11
    when "two" then 2
    when "three" then 3
    when "four" then 4
    when "five" then 5
    when "six" then 6
    when "seven" then 7
    when "eight" then 8
    when "nine" then 9
    when "ten", "jack", "queen", "king" then 10
    else 0
    end
  end

  def self.card_range(card1, card2)
    score = parse_card(card1) + parse_card(card2)
    case
    when score == 21 then "blackjack"
    when score >= 17 then "high"
    when score >= 12 then "mid"
    else "low"
    end
  end

  def self.first_turn(card1, card2, dealer_card)
    if card1 == "ace" && card2 == "ace"
      'P'
    else
      range = card_range(card1, card2)
      dealer_value = parse_card(dealer_card)
      case range
      when "blackjack" then dealer_value >= 10 ? 'S' : 'W'
      when "high" then 'S'
      when "mid" then dealer_value >= 7 ? 'H' : 'S'
      else 'H'
      end
    end
  end
end
