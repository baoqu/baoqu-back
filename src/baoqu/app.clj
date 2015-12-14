(ns baoqu.app
  (:require [catacumba.core :as cat]
            [catacumba.handlers.misc :as misc]
            [baoqu.routes.home :as home]
            [catacumba.handlers.parse :as parse]
            [baoqu.routes.users :as users])
  (:gen-class))

(def app
  (cat/routes [
               [:prefix "api"
                [:any (misc/autoreloader)]
                [:any (parse/body-params)]
                [:get "meta" #'home/hello-baoqu]
                [:prefix "users"
                 [:post "create" #'users/create]]]]))

(defn -main
  [& args]
  (cat/run-server app))
