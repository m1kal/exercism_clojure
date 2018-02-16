(ns space-age)

(def factors {:mercury 0.2408467 :venus 0.61519726
              :mars 1.8808158 :jupiter 11.862615
              :saturn 29.447498 :uranus 84.016846
              :neptune 164.79132})

(defn on-earth [seconds] (/ seconds 365.25 24 60 60))
(defn on-planet [name] (fn [seconds] (/ (on-earth seconds) (factors name))))
(def on-mercury (on-planet :mercury))
(def on-venus (on-planet :venus))
(def on-mars (on-planet :mars))
(def on-jupiter (on-planet :jupiter))
(def on-saturn (on-planet :saturn))
(def on-neptune (on-planet :neptune))
(def on-uranus (on-planet :uranus))
