package com.tfcgourmet.tfc_gourmet.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class BrinzaWheelBlock extends BaseEntityBlock {
    public static final EnumProperty<CheeseAge> AGE = EnumProperty.create("age", CheeseAge.class);
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 1, 4);
    public static final BooleanProperty RACK = BooleanProperty.create("rack");
    public static final BooleanProperty SMOKED = BooleanProperty.create("smoked");
    
    private final RegistryObject<Item> sliceItem;
    
    public enum CheeseAge implements StringRepresentable {
        FRESH("fresh"), AGED("aged"), VINTAGE("vintage");
        
        private final String name;
        
        CheeseAge(String name) {
            this.name = name;
        }
        
        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
    
    public BrinzaWheelBlock(RegistryObject<Item> sliceItem) {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.SNOW)
                .strength(2.0f)
                .requiresCorrectToolForDrops()
                .randomTicks());
        this.sliceItem = sliceItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, CheeseAge.FRESH).setValue(COUNT, 1).setValue(RACK, false).setValue(SMOKED, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrinzaWheelBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        if (type == ModBlockEntities.BRINZA_WHEEL.get()) {
            return (lvl, pos, st, be) -> BrinzaWheelBlockEntity.serverTick(lvl, pos, st, (BrinzaWheelBlockEntity) be);
        }
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, COUNT, RACK, SMOKED);
    }
    
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(RACK)) {
            return;
        }
        CheeseAge currentAge = state.getValue(AGE);
        if (currentAge == CheeseAge.FRESH && random.nextFloat() < 0.1f) {
            level.setBlock(pos, state.setValue(AGE, CheeseAge.AGED), 2);
        } else if (currentAge == CheeseAge.AGED && random.nextFloat() < 0.05f) {
            level.setBlock(pos, state.setValue(AGE, CheeseAge.VINTAGE), 2);
        }
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        
        
        if (heldItem.getItem().toString().contains("knife") || heldItem.getItem().toString().contains("blade")) {
            int currentCount = state.getValue(COUNT);
                        if (sliceItem != null) {
                ItemStack slice = new ItemStack(sliceItem.get());
                if (!player.getInventory().add(slice)) {
                    player.drop(slice, false);
                }
            }
            
            
            if (currentCount < 4) {
                level.setBlock(pos, state.setValue(COUNT, currentCount + 1), 2);
            } else {
            
                level.destroyBlock(pos, false);
            }
            
            return InteractionResult.SUCCESS;
        }
        
        return InteractionResult.PASS;
    }
    

    private static final VoxelShape SHAPE_FULL = Shapes.box(0.125, 0.0, 0.125, 0.875, 0.375, 0.875); // 2,0,2 to 14,6,14
    private static final VoxelShape SHAPE_3_QUARTERS = Shapes.box(0.125, 0.0, 0.125, 0.875, 0.375, 0.875); // Similar to full but with one piece missing
    private static final VoxelShape SHAPE_HALF = Shapes.box(0.125, 0.0, 0.125, 0.875, 0.375, 0.875); // Half the pieces
    private static final VoxelShape SHAPE_QUARTER = Shapes.box(0.125, 0.0, 0.125, 0.875, 0.375, 0.875); // One piece left
    
    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, CollisionContext context) {
        int count = state.getValue(COUNT);
        switch (count) {
            case 1: return SHAPE_FULL;
            case 2: return SHAPE_3_QUARTERS;
            case 3: return SHAPE_HALF;
            case 4: return SHAPE_QUARTER;
            default: return SHAPE_FULL;
        }
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);
    }
}
