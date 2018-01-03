(ns robot-name)

(def all-robots (atom []))

(defn generate-robot-name []
  (clojure.string/join
    (map clojure.string/join
      [
        (repeatedly 2 #(rand-nth "ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
        (repeatedly 3 #(rand-nth (range 0 9)))
      ]
    )
  )
)

(defn generate-unique-name []
  (let [name (atom (generate-robot-name))]
    (while (some #{@name} @all-robots)
      (reset! name (generate-robot-name))
    )
    (swap! all-robots (fn [names] (conj names @name)))
    @name
  )
)

(defn robot []
  (atom (generate-unique-name))
)

(defn robot-name [robot]
  @robot
)

(defn reset-name [robot]
  (reset! all-robots (remove #{(robot-name robot)} @all-robots))
  (reset! robot (generate-unique-name))
)
