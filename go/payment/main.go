package main

import (
	"github.com/go-chassis/go-chassis"
	"github.com/go-chassis/go-chassis/server/restful"
	_ "github.com/huaweicse/auth/adaptor/gochassis" //must import before other imports
	"net/http"

	_ "github.com/go-chassis/go-chassis-cloud/provider/huawei/engine"
)

type PaymentResource struct {
}

func (r *PaymentResource) Get(ctx *restful.Context) {
	ctx.Write([]byte("payment info"))
}

func (r *PaymentResource) URLPatterns() []restful.Route {
	return []restful.Route{
		{
			Method:       http.MethodGet,
			Path:         "/v1/payment",
			ResourceFunc: r.Get,
		},
	}
}
func main() {
	chassis.RegisterSchema("rest", &PaymentResource{})
	err := chassis.Init()
	if err != nil {
		panic(err)
	}
	err = chassis.Run()
	if err != nil {
		panic(err)
	}
}
