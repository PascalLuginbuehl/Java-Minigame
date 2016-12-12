package com.company.Game;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Entity extends Body {

    public Vector velocity;
    public Vector force;
    private int spritePositon;
    public int lastDirection;

    Entity(Vector position, Model model, Vector force, Vector velocity) {
        super(position, model);

        this.velocity = velocity;
        this.force = force;
        this.lastDirection = 0;
    }

//        render(ctx: CanvasRenderingContext2D) {
//            if (this.spritePositon == undefined || Math.ceil(this.spritePositon) >= this.model.spriteMax) {
//                this.spritePositon = 0;
//            }
//
//            let speed = Math.sqrt(this.velocity.y*this.velocity.y + this.velocity.x * this.velocity.x);;
//
//            let direction = this.lastDirection;
//
//            this.spritePositon += speed / 1000;
//            ctx.drawImage(this.model.texture, this.model.textureSize.x * Math.floor(this.spritePositon), 0, this.model.texture.width / this.model.spriteMax, this.model.texture.height, Math.round(this.position.x), Math.round(this.position.y), this.model.textureSize.x, this.model.textureSize.y);
//            // console.log(this.attackRangeObject[this.lastDirection]);
//            this.attackRangeObject[this.lastDirection].drawHitbox(this.position, ctx);
//        }

}
