(ns baoqu.app
  (:require [catacumba.core :as cat]
            [catacumba.handlers.misc :as misc]
            [baoqu.routes.home :as home])
  (:gen-class))

(def app
  (cat/routes [[:any (misc/autoreloader)]
              [:get "" #'home/hello-baoqu]]))

(defn -main
  [& args]
  (cat/run-server app))
