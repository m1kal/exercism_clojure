(ns triangle)

(defn invalid? [sides]
  (if
    (<= (apply + (butlast sides)) (last sides))
    :illogical))


(defn type [& sides]
  (let [distinct_sides (count (set sides))]
  (or
    (invalid? (sort sides))
    (and (= 1 distinct_sides) :equilateral)
    (and (= 2 distinct_sides) :isosceles)
    :scalene)))
