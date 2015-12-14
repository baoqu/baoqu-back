(ns baoqu.app
  (:require [catacumba.core :as cat]
            [baoqu.routes.home :as home])
  (:gen-class))

(defn -main
  [& args]
  (cat/run-server home/hello-baoqu))
