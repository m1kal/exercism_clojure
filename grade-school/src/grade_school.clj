(ns grade-school)

(defn add [school name grade]
  (assoc school grade (vec (conj (school grade) name)))
)

(defn grade [school grade]
  (get school grade [])
)

(defn sorted [school]
  (reduce (fn [sorted [key val]] (assoc sorted key (sort val))) {} (sort school))
)
